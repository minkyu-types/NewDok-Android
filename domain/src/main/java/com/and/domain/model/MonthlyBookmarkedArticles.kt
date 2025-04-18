package com.and.domain.model

data class BookmarkedArticles(
    val totalAmount: Int,
    val bookmarkForMonth: List<MonthlyBookmarkedArticles>
)

data class MonthlyBookmarkedArticles(
    val id: Int,
    val month: String,
    val articles: List<BookmarkedArticle>
)
