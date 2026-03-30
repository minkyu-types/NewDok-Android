package com.and.presentation.model

import com.and.domain.model.type.InterestCategory

data class NewsLetterSubscriptionModel(
    val brandId: Int,
    val brandName: String,
    val profileImageUrl: String,
    val interests: List<InterestCategory>,
    val subscriptionStatus: SubscriptionStatus,
    val shortDescription: String,
    val subscriptionCount: Int,
)
