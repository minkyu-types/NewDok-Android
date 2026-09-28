package com.and.domain.model

sealed class KakaoLoginResult {
    data class Success(val user: User) : KakaoLoginResult()

    /**
     * 신규 회원. signupToken은 /auth/social-login 응답의 accessToken 필드에서 온 값이며
     * 회원가입 완료에만 쓰인다. 인증 토큰으로 저장해서는 안 된다.
     */
    data class NeedSignup(val signupToken: String) : KakaoLoginResult()
}
