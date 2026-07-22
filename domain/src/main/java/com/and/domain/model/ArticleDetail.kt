package com.and.domain.model

import java.time.Instant

data class ArticleDetail(
    val articleId: Int,
    val articleTitle: String,
    val date: Instant,
    val brandId: Int,
    val brandName: String,
    val articleHTML: String,
    val brandImageUrl: String,
    val isBookmarked: Boolean
)
