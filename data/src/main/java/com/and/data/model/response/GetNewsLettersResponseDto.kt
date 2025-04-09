package com.and.data.model.response

data class GetNewsLettersResponseDto(
    val brandId: Int,
    val brandName: String,
    val imageUrl: String,
    val interests: List<com.and.data.model.response.GetNewsLettersResponseDto.Interest>,
    val isSubscribed: String,
    val shortDescription: String,
    val subscriptionCount: Int
) {
    data class Interest(
        val id: Int,
        val name: String
    )
}
