package com.and.data.model.response

import com.and.data.model.data.ArticleDto

data class GetArticlesResponseDto(
    val data: List<DailyArticlesDto>
) {
    data class DailyArticlesDto(
        val id: Int,
        val publishDate: Int,
        val receivedUnread: Int,
        val receivedArticleList: List<ArticleDto>
    )
}