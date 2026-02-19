package com.and.presentation.mapper

import com.and.domain.model.NewsLetter
import com.and.presentation.model.NewsLetterSubscriptionModel
import javax.inject.Inject

class NewsLetterSubscriptionMapperImpl @Inject constructor(): NewsLetterSubscriptionMapper {
    override fun mapToPresentation(input: NewsLetter): NewsLetterSubscriptionModel {
        return NewsLetterSubscriptionModel(
            brandId = input.brandId,
            brandName = input.brandName,
            profileImageUrl = input.imageUrl,
            interests = input.interests,
            shortDescription = input.shortDescription ?: "",
            isSubscribed = input.isSubscribed,
            subscriptionCount = input.subscriptionCount ?: 0
        )
    }


    override fun mapToDomain(input: NewsLetterSubscriptionModel): NewsLetter {
        return NewsLetter(
            brandId = input.brandId,
            brandName = input.brandName,
            imageUrl = input.profileImageUrl,
            interests = input.interests,
            articles = emptyList(),
            shortDescription = input.shortDescription,
            detailDescription = input.shortDescription,
            publicationCycle = "",
            subscribeUrl = "",
            subscriptionCount = input.subscriptionCount,
            isSubscribed = input.isSubscribed
        )
    }
}