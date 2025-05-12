package com.and.presentation.mapper

import com.and.domain.model.NewsLetter
import com.and.presentation.model.NewsLetterModel
import javax.inject.Inject

class NewsLetterMapperImpl @Inject constructor(): NewsLetterMapper {
    override fun mapToPresentation(input: NewsLetter): NewsLetterModel {
        return NewsLetterModel(
            name = input.brandName,
            profileImageUrl = input.imageUrl,
            repeatTerm = input.publicationCycle,
            introduction = input.shortDescription ?: "",
            interests = input.interests,
        )
    }

    override fun mapToDomain(input: NewsLetterModel): NewsLetter {
        return NewsLetter(
            brandId = 0,
            brandName = input.name,
            imageUrl = input.profileImageUrl,
            interests = input.interests,
            articles = emptyList(),
            shortDescription = input.introduction,
            detailDescription = input.introduction,
            publicationCycle = input.repeatTerm,
            subscribeUrl = "",
            subscriptionCount = 0,
            isSubscribed = ""
        )
    }
}