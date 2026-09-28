package com.and.presentation.util

import android.content.Context
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.AuthCodeClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * 카카오 로그인 헬퍼 클래스
 *
 * 인가 코드를 받은 뒤 토큰으로 교환해, 서버가 요구하는 인가 코드와 ID 토큰을 함께 돌려준다.
 * 교환 과정에서 카카오 토큰이 기기에 저장되므로 로그아웃/연결 해제도 클라이언트에서 수행할 수 있다.
 */
object KakaoLoginHelper {

    /**
     * 카카오 로그인을 수행하고 서버 전송에 필요한 값을 반환한다.
     *
     * 카카오톡이 설치되어 있으면 카카오톡으로, 없거나 실패하면 카카오계정(웹)으로 진행한다.
     *
     * @param context Android Context
     * @return 인가 코드와 ID 토큰. 사용자가 로그인을 취소한 경우 null
     * @throws IllegalStateException OpenID Connect가 비활성화되어 ID 토큰을 받지 못한 경우
     * @throws Exception 로그인 실패 시 예외 발생
     */
    suspend fun login(context: Context): KakaoAuth? {
        val authorizationCode = getAuthorizationCode(context) ?: return null
        val token = issueAccessToken(authorizationCode)

        // idToken은 카카오 콘솔에서 OpenID Connect가 켜져 있어야 채워진다.
        // 비어 있는 채로 서버에 보내면 400으로 되돌아오므로 여기서 원인을 드러낸다.
        val idToken = token.idToken
            ?: throw IllegalStateException(
                "카카오 ID 토큰을 받지 못했습니다. " +
                        "카카오 개발자 콘솔 > 카카오 로그인 > OpenID Connect 활성화 여부를 확인하세요."
            )

        return KakaoAuth(
            authorizationCode = authorizationCode,
            idToken = idToken
        )
    }

    /**
     * 카카오 인가 코드를 받아온다.
     *
     * @return 인가 코드. 사용자가 로그인을 취소한 경우 null
     */
    private suspend fun getAuthorizationCode(context: Context): String? =
        suspendCancellableCoroutine { continuation ->
            val callback: (String?, Throwable?) -> Unit = { code, error ->
                if (continuation.isActive) {
                    when {
                        error != null -> {
                            if (error.isCancelled()) {
                                // 취소 시 null을 반환해 호출 측 코루틴이 대기 상태로 남지 않도록 함
                                continuation.resume(null)
                            } else {
                                continuation.resumeWithException(error)
                            }
                        }
                        code != null -> continuation.resume(code)
                        else -> continuation.resumeWithException(
                            IllegalStateException("Kakao login failed: Unknown error")
                        )
                    }
                }
            }

            try {
                if (AuthCodeClient.instance.isKakaoTalkLoginAvailable(context)) {
                    AuthCodeClient.instance.authorizeWithKakaoTalk(context) { code, error ->
                        if (error != null && !error.isCancelled()) {
                            // 카카오톡으로 실패한 경우 카카오계정으로 재시도
                            AuthCodeClient.instance.authorizeWithKakaoAccount(context, callback = callback)
                        } else {
                            callback(code, error)
                        }
                    }
                } else {
                    AuthCodeClient.instance.authorizeWithKakaoAccount(context, callback = callback)
                }
            } catch (e: Exception) {
                if (continuation.isActive) {
                    continuation.resumeWithException(e)
                }
            }
        }

    /**
     * 인가 코드를 토큰으로 교환한다.
     *
     * 발급된 토큰은 SDK가 기기에 저장하므로 이후 logout/unlink가 동작한다.
     * 인가 코드는 1회용이라 이 시점에 소진된다.
     */
    private suspend fun issueAccessToken(authorizationCode: String): OAuthToken =
        suspendCancellableCoroutine { continuation ->
            AuthApiClient.instance.issueAccessToken(authorizationCode) { token, error ->
                if (continuation.isActive) {
                    when {
                        error != null -> continuation.resumeWithException(error)
                        token != null -> continuation.resume(token)
                        else -> continuation.resumeWithException(
                            IllegalStateException("Kakao token issue failed: Unknown error")
                        )
                    }
                }
            }
        }

    /**
     * 카카오 로그아웃
     *
     * 카카오 토큰이 없으면(이메일 가입자, 게스트 등) SDK 호출 없이 정상 종료한다.
     */
    suspend fun logout() {
        if (!AuthApiClient.instance.hasToken()) return

        suspendCancellableCoroutine { continuation ->
            UserApiClient.instance.logout { error ->
                if (continuation.isActive) {
                    if (error != null && !error.isTokenNotFound()) {
                        continuation.resumeWithException(error)
                    } else {
                        // 토큰이 이미 없는 상태(TokenNotFound)는 로그아웃 목적이
                        // 달성된 것이므로 정상 종료로 취급한다
                        continuation.resume(Unit)
                    }
                }
            }
        }
    }

    /**
     * 카카오 연결 해제 (탈퇴)
     *
     * 카카오 토큰이 없으면(이메일 가입자, 게스트 등) SDK 호출 없이 정상 종료한다.
     */
    suspend fun unlink() {
        if (!AuthApiClient.instance.hasToken()) return

        suspendCancellableCoroutine { continuation ->
            UserApiClient.instance.unlink { error ->
                if (continuation.isActive) {
                    if (error != null && !error.isTokenNotFound()) {
                        continuation.resumeWithException(error)
                    } else {
                        continuation.resume(Unit)
                    }
                }
            }
        }
    }

    private fun Throwable.isCancelled(): Boolean =
        this is ClientError && reason == ClientErrorCause.Cancelled

    private fun Throwable.isTokenNotFound(): Boolean =
        this is ClientError && reason == ClientErrorCause.TokenNotFound
}

/** 서버의 /auth/social-login 요청에 필요한 카카오 인증 값 */
data class KakaoAuth(
    val authorizationCode: String,
    val idToken: String
)
