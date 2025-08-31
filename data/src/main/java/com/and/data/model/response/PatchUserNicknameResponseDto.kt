package com.and.data.model.response

import com.squareup.moshi.Json

data class PatchUserNicknameResponseDto(
    val id: Int,
    val loginId: String,
    @Json(name = "nickname") val isNicknameChanged: String
)
