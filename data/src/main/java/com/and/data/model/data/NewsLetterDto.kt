package com.and.data.model.data

import java.time.Instant

data class NewsLetterDto(
    val id: Int,
    val brandName: String,
    val firstDescription: String,
    val secondDescription: String,
    val publicationCycle: String,
    val subscribeUrl: String,
    val imageUrl: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val industries: List<IndustryDto>,
    val interests: List<InterestDto>
)

data class IndustryDto(
    val id: Int,
    val name: String
)
