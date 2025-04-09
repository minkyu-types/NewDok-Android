package com.and.domain.model

data class DailyArticles(
    val id: Int,
    val publishDate: Int,
    val receivedUnread: Int,
    val receivedArticleList: List<Article>
)
