package com.and.data.model.response

import com.and.data.model.data.KakaoProfileDto
import com.and.data.model.data.UserDto

data class KakaoLoginResponseDto(
    val isRegistered: Boolean,
    val accessToken: String? = null,
    val user: UserDto? = null,
    val signupToken: String? = null,
    val profile: KakaoProfileDto? = null
)
