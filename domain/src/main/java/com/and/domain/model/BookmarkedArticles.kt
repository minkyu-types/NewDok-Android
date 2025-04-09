package com.and.domain.model

data class BookmarkedArticles(
    val id: Int,
    val month: String,
    val totalAmount: Int,
    val articles: List<BookmarkedArticle>
)
