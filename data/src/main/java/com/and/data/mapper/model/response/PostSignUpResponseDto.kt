package com.and.data.mapper.model.response

import com.and.data.mapper.model.data.UserDto

data class PostSignUpResponseDto(
    val user: UserDto,
    val accessToken: String
)
