package com.and.data.model.response

data class GetBookmarkedInterestResponseDto(
    val data: List<com.and.data.model.response.GetBookmarkedInterestResponseDto.Interest>
) {
    data class Interest(
        val id: Int,
        val name: String
    )
}
