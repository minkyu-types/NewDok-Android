package com.and.domain.model

data class DailyArticleStatus(
    val publishDate: Int,
    val hasArticles: Boolean,
    val totalCount: Int,
    val unreadCount: Int,
)
