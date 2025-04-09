package com.and.data.model.response

import com.and.data.model.data.ArticleDto

data class GetTodayArticlesResponseDto(
    val publishDate: Int,
    val receivedUnread: Int,
    val receivedArticleList: List<com.and.data.model.data.ArticleDto>
)
