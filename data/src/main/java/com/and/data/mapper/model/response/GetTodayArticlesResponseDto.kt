package com.and.data.mapper.model.response

import com.and.data.mapper.model.data.ArticleDto

data class GetTodayArticlesResponseDto(
    val publishDate: Int,
    val receivedUnread: Int,
    val receivedArticleList: List<ArticleDto>
)
