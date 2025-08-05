package com.and.data.model.response

import com.and.data.model.data.UserRegisterDto

data class PostSignUpResponseDto(
    val user: UserRegisterDto,
    val accessToken: String
)
