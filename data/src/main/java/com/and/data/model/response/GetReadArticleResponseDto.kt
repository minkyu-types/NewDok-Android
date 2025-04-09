package com.and.data.model.response

import java.time.Instant

data class GetReadArticleResponseDto(
    val data: com.and.data.model.response.GetReadArticleResponseDto.Article
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
