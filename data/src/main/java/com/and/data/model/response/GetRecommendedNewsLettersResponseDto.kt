package com.and.data.model.response

import java.time.Instant

data class GetRecommendedNewsLettersResponseDto(
    val newsLetters: List<NewsLetter>
) {
    data class NewsLetter(
        val id: Int,
        val brandName: String,
        val firstDescription: String,
        val secondDescription: String,
        val publicationCycle: String,
        val subscribeUrl: String,
        val imageUrl: String,
        val createdAt: Instant,
        val updatedAt: Instant,
        val industries: List<Industry>,
        val interests: List<Interest>
    )

    data class Industry(
        val id: Int,
        val name: String
    )

    data class Interest(
        val id: Int,
        val name: String
    )
}
