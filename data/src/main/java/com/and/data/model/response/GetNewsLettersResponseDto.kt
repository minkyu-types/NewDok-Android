package com.and.data.model.response

import com.and.data.model.data.InterestDto

data class GetNewsLettersResponseDto(
    val newsLetters: List<NewsLetterDetailDto>
) {
    data class NewsLetterDetailDto(
        val brandId: Int,
        val brandName: String,
        val imageUrl: String,
        val interests: List<InterestDto>,
        val isSubscribed: String? = null,
        val shortDescription: String? = null,
        val subscriptionCount: Int? = null,
        val createdAt: String? = null
    )
}
