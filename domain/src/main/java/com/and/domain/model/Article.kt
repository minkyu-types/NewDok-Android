package com.and.domain.model

import com.and.domain.model.type.ArticleStatus

data class Article(
    val brandName: String,
    val imageUrl: String,
    val title: String,
    val articleId: Int,
    val status: ArticleStatus
)