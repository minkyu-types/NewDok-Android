package com.and.data.model.response

import com.and.data.model.data.UserDto

/** POST /auth/social-login/signup 응답 */
data class KakaoSignupResponseDto(
    val isRegistered: Boolean,
    val accessToken: String,
    val user: UserDto
)
