package com.and.presentation.util

import android.content.Context
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.AuthCodeClient
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * 카카오 로그인 헬퍼 클래스
 *
 * 카카오 SDK로 인가 코드(authorization code)를 받아 서버에 전달한다.
 * 인가 코드를 액세스 토큰으로 교환하는 것은 서버의 책임이므로, 클라이언트는 코드를 교환하지 않는다.
 * 인가 코드는 1회용이라 클라이언트가 교환해버리면 서버가 다시 쓸 수 없다.
 */
object KakaoLoginHelper {

    /**
     * 카카오 인가 코드를 받아온다.
     *
     * 카카오톡이 설치되어 있으면 카카오톡으로, 없거나 실패하면 카카오계정(웹)으로 진행한다.
     *
     * @param context Android Context
     * @return 인가 코드. 사용자가 로그인을 취소한 경우 null
     * @throws Exception 로그인 실패 시 예외 발생
     */
    suspend fun getAuthorizationCode(context: Context): String? =
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
     * 카카오 로그아웃
     *
     * 인가 코드 방식에서는 기기에 카카오 토큰이 저장되지 않으므로 대개 아무 일도 하지 않는다.
     * 과거 빌드에서 남은 토큰이 있을 때만 실제로 로그아웃된다.
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
     * 인가 코드 방식에서는 기기에 카카오 토큰이 없어 클라이언트가 연결 해제를 수행할 수 없다.
     * 실제 연결 해제는 카카오 토큰을 보유한 서버가 탈퇴 처리 시 수행해야 한다.
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
