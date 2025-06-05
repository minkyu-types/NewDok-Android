package com.and.data.model.response

import com.squareup.moshi.Json

data class PatchUserPhoneNumberResponseDto(
    val id: Int,
    val loginId: String,
    @Json(name = "phoneNumber") val isPhoneNumberChanged: Boolean
)
