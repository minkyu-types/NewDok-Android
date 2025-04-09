package com.and.data.model.response

data class GetSubscribedNewsLettersResponseDto(
    val newsLetters: List<com.and.data.model.response.GetSubscribedNewsLettersResponseDto.NewsLetter>
) {
    data class NewsLetter(
        val id: Int,
        val brandName: String,
        val imageUrl: String,
        val publicationCycle: String
    )
}
