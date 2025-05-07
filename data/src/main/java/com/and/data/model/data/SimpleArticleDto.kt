package com.and.data.model.data

import java.time.Instant

data class SimpleArticleDto(
    val id: Int,
    val title: String,
    val date: Instant
)