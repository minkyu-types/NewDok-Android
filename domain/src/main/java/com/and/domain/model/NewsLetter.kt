package com.and.domain.model

import com.and.domain.model.type.InterestCategory

data class NewsLetter(
    val brandId: Int,
    val brandName: String,
    val imageUrl: String,
    val interests: List<InterestCategory> = emptyList(),
    val articles: List<SimpleArticle> = emptyList(),
    val shortDescription: String? = null,
    val detailDescription: String,
    val publicationCycle: String,
    val subscribeUrl: String,
    val subscriptionCount: Int? = 0,
    val isSubscribed: String
)