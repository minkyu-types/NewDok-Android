package com.and.data.model.response

data class GetNewsLetterByNameResponseDto(
    val id: Int,
    val brandName: String,
    val firstDescription: String,
    val imageUrl: String,
)