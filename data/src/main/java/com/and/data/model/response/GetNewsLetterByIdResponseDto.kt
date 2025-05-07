package com.and.data.model.response

import com.and.data.model.data.InterestDto
import com.and.data.model.data.SimpleArticleDto

data class GetNewsLetterByIdResponseDto(
    val brandId: Int,
    val brandName: String,
    val imageUrl: String,
    val interests: List<InterestDto>,
    val brandArticleList: List<SimpleArticleDto>,
    val detailDescription: String,
    val publicationCycle: String,
    val subscribeUrl: String,
    val subscribeCheck: Boolean,
    val isSubscribed: String
)