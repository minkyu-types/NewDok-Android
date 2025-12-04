package com.and.domain.model

data class DailyArticle(
    val publishDate: Int,
    val hasArticles: Boolean,
    val totalCount: Int,
    val unreadCount: Int,
)
