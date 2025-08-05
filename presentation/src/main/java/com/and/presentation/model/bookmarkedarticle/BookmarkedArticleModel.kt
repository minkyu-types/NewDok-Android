package com.and.presentation.model.bookmarkedarticle

data class BookmarkedArticleModel(
    val brandName: String,
    val brandId: Int,
    val articleTitle: String,
    val articleId: Int,
    val sampleText: String,
    val date: String,
    val thumbnailImageUrl: String?
)
