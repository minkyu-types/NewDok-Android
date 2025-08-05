package com.and.data.model.response

import com.squareup.moshi.Json

data class GetArticlesCountResponseDto(
     @Json(name = "count") val count: Int
)