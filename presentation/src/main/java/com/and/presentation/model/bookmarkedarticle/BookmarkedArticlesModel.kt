package com.and.presentation.model.bookmarkedarticle

data class BookmarkedArticlesModel(
    val totalAmount: Int,
    val bookmarkForMonth: List<MonthlyBookmarkedArticlesModel>
)
