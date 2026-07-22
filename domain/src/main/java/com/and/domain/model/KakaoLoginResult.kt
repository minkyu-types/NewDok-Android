package com.and.domain.model

sealed class KakaoLoginResult {
    data class Success(val user: User) : KakaoLoginResult()
    data class NeedSignup(
        val signupToken: String,
        val profile: KakaoProfile
    ) : KakaoLoginResult()
}
