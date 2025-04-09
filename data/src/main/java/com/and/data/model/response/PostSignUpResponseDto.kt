package com.and.data.model.response

import com.and.data.model.data.UserDto

data class PostSignUpResponseDto(
    val user: com.and.data.model.data.UserDto,
    val accessToken: String
)
