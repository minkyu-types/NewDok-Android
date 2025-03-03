package com.and.presentation.model

data class DailyArticleModel(
    val brandName: String,
    val imageUrl: String?,
    val articleTitle: String,
    val articleId: Int,
    val status: String
) {
    fun isRead() = this.status != "Unread"
}
