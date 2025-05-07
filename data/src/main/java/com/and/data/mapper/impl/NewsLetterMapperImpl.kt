package com.and.data.mapper.impl

import com.and.data.mapper.NewsLetterMapper
import com.and.data.model.data.NewsLetterDto
import com.and.data.model.data.UserInterestDto
import com.and.domain.model.NewsLetter
import com.and.domain.model.type.InterestCategory
import java.time.Instant
import javax.inject.Inject

class NewsLetterMapperImpl @Inject constructor(

): NewsLetterMapper {
    override fun mapToData(input: NewsLetter): NewsLetterDto {
        return NewsLetterDto(
            brandId = input.brandId,
            brandName = input.brandName,
            briefDescription = input.shortDescription ?: "",
            publicationCycle = input.publicationCycle,
            subscribeUrl = input.subscribeUrl,
            imageUrl = input.imageUrl,
            interests = input.interests.map {
                UserInterestDto(
                    0,
                    it.id,
                    Instant.now()
                )
            }
        )
    }

    override fun mapToDomain(input: NewsLetterDto): NewsLetter {
        return NewsLetter(
            brandId = input.brandId,
            brandName = input.brandName,
            imageUrl = input.imageUrl,
            interests = input.interests.map {
                InterestCategory.getInterestById(it.interestId)
            },
            articles = emptyList(),
            shortDescription = input.briefDescription,
            detailDescription = input.briefDescription,
            publicationCycle = input.publicationCycle,
            subscribeUrl = input.subscribeUrl,
            subscriptionCount = 0,
            isSubscribed = "Subscribed"
        )
    }
}