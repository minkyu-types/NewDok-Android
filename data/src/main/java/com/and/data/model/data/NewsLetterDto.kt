package com.and.data.model.data

data class NewsLetterDto(
    val brandId: Int,
    val brandName: String,
    val briefDescription: String,
    val publicationCycle: String,
    val subscribeUrl: String,
    val imageUrl: String,
    val interests: List<UserInterestDto>
)
