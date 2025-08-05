package com.and.data.model.response

import com.and.data.model.data.ArticleDto
import com.squareup.moshi.Json

data class GetArticlesResponseDto(
     @Json(name = "data") val data: List<DailyArticlesDto>
) {
    data class DailyArticlesDto(
        val id: Int?,
        val publishDate: Int,
        val receivedUnread: Int,
        val receivedArticleList: List<ArticleDto>
    )
}