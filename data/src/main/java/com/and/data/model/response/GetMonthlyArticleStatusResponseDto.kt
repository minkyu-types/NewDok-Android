package com.and.data.model.response

import com.squareup.moshi.Json

data class GetMonthlyArticleStatusResponseDto(
     @Json(name = "data") val data: List<DailyArticleStatusDto>
) {
    data class DailyArticleStatusDto(
        val publishDate: Int,
        val hasArticles: Boolean,
        val totalCount: Int,
        val unreadCount: Int,
    )
}