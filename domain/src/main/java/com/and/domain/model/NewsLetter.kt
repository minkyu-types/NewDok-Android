package com.and.domain.model

import com.and.domain.model.type.InterestCategory
import java.time.Instant

data class NewsLetter(
    val brandId: Int,
    val brandName: String,
    val imageUrl: String,
    val interests: List<InterestCategory> = emptyList(),
    val articles: List<BrandArticle> = emptyList(),
    val shortDescription: String,
    val detailDescription: String,
    val publicationCycle: String,
    val subscribeUrl: String,
    val subscriptionCount: Int,
    val isSubscribed: Boolean = false
) {
    data class BrandArticle(
        val id: Int,
        val title: String,
        val date: Instant
    )
}
