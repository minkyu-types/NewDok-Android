package com.and.data.mapper.impl

import com.and.data.mapper.NewsLetterMapper
import com.and.data.model.data.IndustryDto
import com.and.data.model.data.InterestDto
import com.and.data.model.data.NewsLetterDto
import com.and.domain.model.NewsLetter
import com.and.domain.model.type.InterestCategory
import java.time.Instant
import javax.inject.Inject

class NewsLetterMapperImpl @Inject constructor(

): NewsLetterMapper {
    override fun mapToData(input: NewsLetter): NewsLetterDto {
        return NewsLetterDto(
            id = input.brandId,
            brandName = input.brandName,
            firstDescription = input.shortDescription ?: "",
            secondDescription = input.detailDescription,
            publicationCycle = input.publicationCycle,
            subscribeUrl = input.subscribeUrl,
            imageUrl = input.imageUrl,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            industries = emptyList(),
            interests = input.interests.map {
                InterestDto(
                    id = it.id,
                    name = it.value
                )
            }
        )
    }

    override fun mapToDomain(input: NewsLetterDto): NewsLetter {
        return NewsLetter(
            brandId = input.id,
            brandName = input.brandName,
            imageUrl = input.imageUrl,
            interests = input.interests.mapNotNull {
                InterestCategory.getInterestById(it.id)
            },
            articles = emptyList(),
            shortDescription = input.firstDescription,
            detailDescription = input.secondDescription,
            publicationCycle = input.publicationCycle,
            subscribeUrl = input.subscribeUrl,
            subscriptionCount = 0,
            isSubscribed = "Subscribed"
        )
    }
}