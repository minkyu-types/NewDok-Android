package com.and.presentation.mapper

import com.and.domain.model.NewsLetter
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.model.SubscriptionStatus
import javax.inject.Inject

class NewsLetterMapperImpl @Inject constructor(): NewsLetterMapper {
    override fun mapToPresentation(input: NewsLetter): NewsLetterModel {
        return NewsLetterModel(
            id = input.brandId,
            name = input.brandName,
            profileImageUrl = input.imageUrl,
            repeatTerm = input.publicationCycle,
            introduction = input.shortDescription ?: "",
            interests = input.interests,
            subscriptionStatus = SubscriptionStatus.from(input.isSubscribed),
        )
    }

    override fun mapToDomain(input: NewsLetterModel): NewsLetter {
        return NewsLetter(
            brandId = input.id,
            brandName = input.name,
            imageUrl = input.profileImageUrl,
            interests = input.interests,
            articles = emptyList(),
            shortDescription = input.introduction,
            detailDescription = input.introduction,
            publicationCycle = input.repeatTerm,
            subscribeUrl = "",
            subscriptionCount = 0,
            isSubscribed = input.subscriptionStatus.name
        )
    }
}