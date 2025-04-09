package com.and.data.model.response

import com.and.data.model.data.ArticleDto

data class GetArticlesResponseDto(
    val data: List<com.and.data.model.response.GetArticlesResponseDto.DailyArticle>
) {
    data class DailyArticle(
        val id: Int,
        val publishDate: Int,
        val receivedUnread: Int,
        val receivedArticleList: List<com.and.data.model.data.ArticleDto>
    )
}