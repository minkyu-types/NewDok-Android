package com.and.domain.model

data class BookmarkedArticle(
    val brandName: String,
    val brandId: Int,
    val articleTitle: String,
    val articleId: Int,
    val sampleText: String,
    val date: String,
    val thumbnailImageUrl: String?
)
