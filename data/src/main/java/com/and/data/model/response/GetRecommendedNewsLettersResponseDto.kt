package com.and.data.model.response

import com.and.data.model.data.InterestDto
import com.squareup.moshi.Json
import java.time.Instant

data class GetRecommendedNewsLettersResponseDto(
    @Json(name = "intersection") val intersectionNewsLetters: List<RecommendedNewsLetterDto>,
    @Json(name = "union") val unionNewsLetters: List<RecommendedNewsLetterDto>
) {
    data class RecommendedNewsLetterDto(
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
        val interests: List<InterestDto>
    )

    data class Industry(
        val id: Int,
        val name: String
    )
}
