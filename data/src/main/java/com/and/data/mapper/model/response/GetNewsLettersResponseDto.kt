package com.and.data.mapper.model.response

data class GetNewsLettersResponseDto(
    val brandId: Int,
    val brandName: String,
    val imageUrl: String,
    val interests: List<Interest>,
    val isSubscribed: String,
    val shortDescription: String,
    val subscriptionCount: Int
) {
    data class Interest(
        val id: Int,
        val name: String
    )
}
