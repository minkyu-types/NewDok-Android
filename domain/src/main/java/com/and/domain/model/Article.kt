package com.and.domain.model

data class Article(
    val brandName: String,
    val imageUrl: String,
    val title: String,
    val articleId: Int,
    val status: ArticleStatus
)

enum class ArticleStatus {
    READ,
    UNREAD
}