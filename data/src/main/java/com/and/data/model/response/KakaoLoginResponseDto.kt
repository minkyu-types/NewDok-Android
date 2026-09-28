package com.and.data.model.response

import com.and.data.model.data.UserDto

/**
 * POST /auth/social-login 응답
 *
 * isRegistered에 따라 accessToken 필드의 의미가 달라진다.
 * - true  : 서비스 액세스 토큰. user에 회원 정보가 담긴다.
 * - false : 회원가입에 사용할 signupToken. user는 null이다.
 */
data class KakaoLoginResponseDto(
    val isRegistered: Boolean,
    val accessToken: String,
    val user: UserDto? = null
)
