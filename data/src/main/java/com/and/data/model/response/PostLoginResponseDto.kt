package com.and.data.model.response

import com.and.data.model.data.UserDto
import com.squareup.moshi.Json

data class PostLoginResponseDto(
    @Json(name = "user") val user: UserDto,
    @Json(name = "accessToken") val accessToken: String
)
