package com.and.domain.model

import java.time.Instant

sealed class SearchResult {

    data class SearchedNewsLetter(
        val id: Int,
        val brandName: String,
        val firstDescription: String? = null,
        val imageUrl: String?
    )

    data class SearchedArticle(
        val id: Int,
        val title: String,
        val matchedText: String,
        val date: Instant,
        val newsLetter: SearchedNewsLetter,
        val matchType: String,
    )
}