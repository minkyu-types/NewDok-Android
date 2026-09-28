package com.and.data.model.request

/**
 * POST /auth/social-login 요청 본문
 *
 * 서버가 idToken을 필수 문자열로 검증하므로 nullable로 두지 않는다.
 * nullable이면 값이 없을 때 Gson이 키 자체를 생략해
 * "idToken should not be empty" 400으로 되돌아온다.
 */
data class KakaoLoginRequestDto(
    val provider: String,
    val platform: String,
    val idToken: String,
    val authorizationCode: String
)
