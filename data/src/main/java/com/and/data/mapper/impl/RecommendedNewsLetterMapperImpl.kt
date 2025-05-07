package com.and.data.mapper.impl

import com.and.data.mapper.RecommendedNewsLetterMapper
import com.and.data.model.data.InterestDto
import com.and.data.model.response.GetRecommendedNewsLettersResponseDto
import com.and.domain.model.RecommendedNewsLetter
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.InterestCategory
import javax.inject.Inject

class RecommendedNewsLetterMapperImpl @Inject constructor(): RecommendedNewsLetterMapper {
    override fun mapToData(input: RecommendedNewsLetter): GetRecommendedNewsLettersResponseDto.RecommendedNewsLetterDto {
        return GetRecommendedNewsLettersResponseDto.RecommendedNewsLetterDto(
            id = input.id,
            brandName = input.brandName,
            firstDescription = input.firstDescription,
            secondDescription = input.secondDescription,
            publicationCycle = input.publicationCycle,
            subscribeUrl = input.subscribeUrl,
            imageUrl = input.imageUrl,
            createdAt = input.createdAt,
            updatedAt = input.updatedAt,
            industries = input.industries.map { industry ->
                GetRecommendedNewsLettersResponseDto.Industry(
                    id = industry.id,
                    name = industry.value
                )
            },
            interests = input.interests.map { interest ->
                InterestDto(
                    id = interest.id,
                    name = interest.value
                )
            }
        )
    }

    override fun mapToDomain(input: GetRecommendedNewsLettersResponseDto.RecommendedNewsLetterDto): RecommendedNewsLetter {
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
            industries = input.industries.map { industry ->
                IndustryCategory.getIndustryById(industry.id)
            },
            interests = input.interests.map { interest ->
                InterestCategory.getInterestById(interest.id)
            }
        )
    }
}