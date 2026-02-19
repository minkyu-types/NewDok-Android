package com.and.data.mapper.impl

import com.and.data.mapper.NewsLetterDetailMapper
import com.and.data.model.data.InterestDto
import com.and.data.model.response.GetNewsLettersResponseDto
import com.and.domain.model.NewsLetter
import com.and.domain.model.type.InterestCategory
import javax.inject.Inject

class NewsLetterDetailMapperImpl @Inject constructor(

): NewsLetterDetailMapper {
    override fun mapToData(input: NewsLetter): GetNewsLettersResponseDto.NewsLetterDetailDto {
        return GetNewsLettersResponseDto.NewsLetterDetailDto(
            brandId = input.brandId,
            brandName = input.brandName,
            imageUrl = input.imageUrl,
            interests = input.interests.map { interest ->
                InterestDto(
                    id = interest.id,
                    name = interest.value
                )
            },
            isSubscribed = input.isSubscribed.toString(),
            shortDescription = input.shortDescription ?: "",
            subscriptionCount = input.subscriptionCount ?: 0
        )
    }

    /**
     * TODO
     * 매핑이 필요한가?
     */
    override fun mapToDomain(input: GetNewsLettersResponseDto.NewsLetterDetailDto): NewsLetter {
        return NewsLetter(
            brandId = input.brandId,
            brandName = input.brandName,
            imageUrl = input.imageUrl,
            interests = input.interests.mapNotNull { interest ->
                InterestCategory.getInterestById(interest.id)
            },
            articles = emptyList(),
            shortDescription = input.shortDescription,
            detailDescription = "",
            publicationCycle = "",
            subscribeUrl = "",
            isSubscribed = input.isSubscribed ?: "INITIAL",
            subscriptionCount = input.subscriptionCount
        )
    }
}