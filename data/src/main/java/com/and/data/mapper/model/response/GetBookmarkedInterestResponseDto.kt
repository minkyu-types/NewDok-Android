package com.and.data.mapper.model.response

data class GetBookmarkedInterestResponseDto(
    val data: List<Interest>
) {
    data class Interest(
        val id: Int,
        val name: String
    )
}
