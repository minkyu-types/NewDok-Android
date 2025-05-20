package com.and.presentation.model.bookmarkedarticle

data class MonthlyBookmarkedArticlesModel(
    val id: Int,
    val month: String,
    val articles: List<BookmarkedArticleModel>
)
