package com.and.data.model.response

import com.and.data.model.data.UserDto

data class KakaoSignupResponseDto(
    val accessToken: String,
    val user: UserDto
)
