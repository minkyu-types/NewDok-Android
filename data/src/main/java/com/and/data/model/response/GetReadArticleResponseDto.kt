package com.and.data.model.response

import java.time.Instant

data class GetReadArticleResponseDto(
    val articleTitle: String,
    val articleid: String,
    val date: Instant,
    val brandId: Int,
    val brandName: String,
    val articleHTML: String,
    val brandImageUrl: String,
    val isBookmarked: Boolean
)
