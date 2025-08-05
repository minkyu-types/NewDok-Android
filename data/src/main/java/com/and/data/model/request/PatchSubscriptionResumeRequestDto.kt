package com.and.data.model.request

import com.squareup.moshi.Json

data class PatchSubscriptionResumeRequestDto(
    @Json(name = "newsletterId") val newsLetterId: String
)
