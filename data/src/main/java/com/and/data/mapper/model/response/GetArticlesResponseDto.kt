package com.and.data.mapper.model.response

import com.and.data.mapper.model.data.ArticleDto

data class GetArticlesResponseDto(
    val data: List<DailyArticle>
) {
    data class DailyArticle(
        val id: Int,
        val publishDate: Int,
        val receivedUnread: Int,
        val receivedArticleList: List<ArticleDto>
    )
}