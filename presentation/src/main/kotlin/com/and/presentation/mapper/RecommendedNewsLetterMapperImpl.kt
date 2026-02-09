package com.and.presentation.mapper

import com.and.domain.model.RecommendedNewsLetter
import com.and.presentation.model.RecommendedNewsLetterModel
import javax.inject.Inject

class RecommendedNewsLetterMapperImpl @Inject constructor(

): RecommendedNewsLetterMapper {
    override fun mapToPresentation(input: RecommendedNewsLetter): RecommendedNewsLetterModel {
        return RecommendedNewsLetterModel(
            id = input.id,
            brandName = input.brandName,
            firstDescription = input.firstDescription,
            secondDescription = input.secondDescription,
            publicationCycle = input.publicationCycle,
            subscribeUrl = input.subscribeUrl,
            imageUrl = input.imageUrl,
            createdAt = input.createdAt,
            updatedAt = input.updatedAt,
            industries = input.industries,
            interests = input.interests
        )
    }

    override fun mapToDomain(input: RecommendedNewsLetterModel): RecommendedNewsLetter {
        return RecommendedNewsLetter(
            id = input.id,
            brandName = input.brandName,
            firstDescription = input.firstDescription,
            secondDescription = input.secondDescription,
            publicationCycle = input.publicationCycle,
            subscribeUrl = input.subscribeUrl,
            imageUrl = input.imageUrl,
            createdAt = input.createdAt,
            updatedAt = input.updatedAt,
            industries = input.industries,
            interests = input.interests
        )
    }
}