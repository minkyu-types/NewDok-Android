package com.and.domain.model

import java.time.Instant

data class SimpleArticle(
    val id: Int,
    val title: String,
    val date: Instant
)
