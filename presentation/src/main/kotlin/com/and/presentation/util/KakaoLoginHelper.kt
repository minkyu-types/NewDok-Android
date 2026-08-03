package com.and.presentation.util

import android.content.Context
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
 * 카카오 SDK를 사용하여 OAuth 로그인을 수행하고 Access Token을 반환합니다.
 */
object KakaoLoginHelper {

    /**
     * 카카오 로그인을 수행하고 Access Token을 반환합니다.
     *
     * @param context Android Context
     * @return Access Token (카카오 액세스 토큰), 사용자가 로그인을 취소한 경우 null
     * @throws Exception 로그인 실패 시 예외 발생
     */
    suspend fun login(context: Context): String? = suspendCancellableCoroutine { continuation ->
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            when {
                error != null -> {
                    if (continuation.isActive) {
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            continuation.resume(null)
                        } else {
                            continuation.resumeWithException(error)
                        }
                    }
                }
                token != null -> {
                    if (continuation.isActive) {
                        continuation.resume(token.accessToken)
                    }
                }
                else -> {
                    if (continuation.isActive) {
                        continuation.resumeWithException(
                            IllegalStateException("Kakao login failed: Unknown error")
                        )
                    }
                }
            }
        }

        try {
            // 카카오톡 설치 확인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                // 카카오톡으로 로그인
                UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                    if (error != null) {
                        // 사용자가 카카오톡 로그인을 취소한 경우
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            // 취소 시 null을 반환해 호출 측 코루틴이 대기 상태로 남지 않도록 함
                            if (continuation.isActive) {
                                continuation.resume(null)
                            }
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡으로 로그인 실패 시 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                    } else {
                        callback(token, error)
                    }
                }
            } else {
                // 카카오계정으로 로그인
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            }
        } catch (e: Exception) {
            if (continuation.isActive) {
                continuation.resumeWithException(e)
            }
        }
    }

    /**
     * 현재 로그인한 사용자 정보를 가져옵니다.
     */
    suspend fun getUserInfo(): KakaoUserInfo? = suspendCancellableCoroutine { continuation ->
        UserApiClient.instance.me { user, error ->
            when {
                error != null -> {
                    if (continuation.isActive) {
                        continuation.resumeWithException(error)
                    }
                }
                user != null -> {
                    if (continuation.isActive) {
                        continuation.resume(
                            KakaoUserInfo(
                                id = user.id ?: 0L,
                                email = user.kakaoAccount?.email,
                                nickname = user.kakaoAccount?.profile?.nickname
                            )
                        )
                    }
                }
                else -> {
                    if (continuation.isActive) {
                        continuation.resume(null)
                    }
                }
            }
        }
    }

    /**
     * 카카오 로그아웃
     */
    suspend fun logout(): Unit = suspendCancellableCoroutine { continuation ->
        UserApiClient.instance.logout { error ->
            if (continuation.isActive) {
                if (error != null) {
                    continuation.resumeWithException(error)
                } else {
                    continuation.resume(Unit)
                }
            }
        }
    }

    /**
     * 카카오 연결 해제 (탈퇴)
     */
    suspend fun unlink(): Unit = suspendCancellableCoroutine { continuation ->
        UserApiClient.instance.unlink { error ->
            if (continuation.isActive) {
                if (error != null) {
                    continuation.resumeWithException(error)
                } else {
                    continuation.resume(Unit)
                }
            }
        }
    }
}

data class KakaoUserInfo(
    val id: Long,
    val email: String?,
    val nickname: String?
)
