package com.and.presentation.model

import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.InterestCategory
import java.time.Instant

data class RecommendedNewsLetterModel(
    val id: Int,
    val brandName: String,
    val firstDescription: String,
    val secondDescription: String,
    val publicationCycle: String,
    val subscribeUrl: String,
    val imageUrl: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val industries: List<IndustryCategory>,
    val interests: List<InterestCategory>
)
