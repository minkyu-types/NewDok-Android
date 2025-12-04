package com.and.data.model.response

import com.and.data.model.data.NewsLetterDto
import com.squareup.moshi.Json

data class GetArticlesByDateResponseDto(
     @Json(name = "data") val data: List<DailyArticleDto>
) {
    data class DailyArticleDto(
        val id: Int,
        val title: String,
        val publishDate: Int,
        val status: String,
        val newsletter: NewsLetterDto
    )
}