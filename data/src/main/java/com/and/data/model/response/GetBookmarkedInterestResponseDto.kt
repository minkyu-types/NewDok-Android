package com.and.data.model.response

data class GetBookmarkedInterestResponseDto(
    val data: List<Interest>
) {
    data class Interest(
        val id: Int,
        val name: String
    )
}
