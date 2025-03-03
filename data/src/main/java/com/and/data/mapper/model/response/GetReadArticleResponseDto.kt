package com.and.data.mapper.model.response

import java.time.Instant

data class GetReadArticleResponseDto(
    val data: Article
) {
    data class Article(
        val articleTitle: String,
        val articleId: String,
        val date: Instant,
        val brandId: Int,
        val brandName: String,
        val articleHTML: String,
        val brandImageUrl: String,
        val isBookmarked: Boolean
    )
}
