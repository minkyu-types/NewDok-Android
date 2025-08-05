package com.and.data.model.request

import com.squareup.moshi.Json

data class PostSMSAuthRequestDto(
    @Json(name = "phoneNumber") val phoneNumber: String
)