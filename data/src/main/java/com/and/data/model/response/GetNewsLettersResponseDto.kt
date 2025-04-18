package com.and.data.model.response

data class GetNewsLettersResponseDto(
    val newsLetters: List<NewsLetterDetailDto>
) {
    data class NewsLetterDetailDto(
        val brandId: Int,
        val brandName: String,
        val imageUrl: String,
        val interests: List<Interest>,
        val isSubscribed: String,
        val shortDescription: String,
        val subscriptionCount: Int
    )

    data class Interest(
        val id: Int,
        val name: String
    )
}
