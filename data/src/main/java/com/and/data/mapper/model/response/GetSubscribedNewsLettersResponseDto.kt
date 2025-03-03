package com.and.data.mapper.model.response

data class GetSubscribedNewsLettersResponseDto(
    val newsLetters: List<NewsLetter>
) {
    data class NewsLetter(
        val id: Int,
        val brandName: String,
        val imageUrl: String,
        val publicationCycle: String
    )
}
