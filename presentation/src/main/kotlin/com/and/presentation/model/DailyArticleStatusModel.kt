package com.and.presentation.model

data class DailyArticleStatusModel(
    val publishDate: Int,
    val hasArticles: Boolean,
    val totalCount: Int,
    val unreadCount: Int,
)
