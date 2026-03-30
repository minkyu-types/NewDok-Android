package com.and.presentation.model

import com.and.domain.model.type.InterestCategory
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

data class NewsLetterDetailModel(
    val brandId: Int,
    val brandName: String,
    val imageUrl: String,
    val interests: List<InterestCategory>,
    val brandArticleList: List<BrandArticleModel>,
    val detailDescription: String,
    val publicationCycle: String,
    val subscribeUrl: String,
    val subscribeCheck: Boolean,
    val subscriptionStatus: SubscriptionStatus
) {

    data class BrandArticleModel (
        val id: Int,
        val title: String,
        val date: ZonedDateTime
    )

    companion object {
        fun getIsSubscribed(newsLetterDetail: NewsLetterDetailModel): Boolean {
            return (newsLetterDetail.subscriptionStatus == SubscriptionStatus.CONFIRMED)
        }
    }
}
