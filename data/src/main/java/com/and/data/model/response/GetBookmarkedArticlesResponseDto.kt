package com.and.data.model.response

import com.squareup.moshi.Json

data class GetBookmarkedArticlesResponseDto(
    @Json(name = "data") val data: BookmarkedArticleData
) {
    data class BookmarkedArticleData(
        val totalAmount: Int,
        val bookmarkForMonth: List<MonthlyBookmarkedArticle>
    )

    data class MonthlyBookmarkedArticle(
        val id: Int,
        val month: String,
        val bookmark: List<BookmarkedArticle>
    )

    data class BookmarkedArticle(
        val brandName: String,
        val brandId: Int,
        val articleTitle: String,
        val articleId: Int,
        val sampleText: String,
        val date: String,
        val imageURL: String?
    )
}
