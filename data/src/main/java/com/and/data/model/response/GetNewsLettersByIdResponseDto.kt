package com.and.data.model.response

import java.time.Instant

data class GetNewsLettersByIdResponseDto(
    val brandId: Int,
    val brandName: String,
    val detailDescription: String,
    val interests: List<com.and.data.model.response.GetNewsLettersByIdResponseDto.Interest>,
    val publicationCycle: String,
    val subscribeUrl: String,
    val imageUrl: String,
    val brandArticleList: List<com.and.data.model.response.GetNewsLettersByIdResponseDto.BrandArticle>,
    val isSubscribed: String,
    val subscribeCheck: Boolean
) {
    data class Interest(
        val id: Int,
        val name: String
    )

    data class BrandArticle(
        val id: Int,
        val title: String,
        val date: Instant
    )
}
