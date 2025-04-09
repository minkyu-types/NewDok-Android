package com.and.data.model.response

data class GetUnSubscribedNewsLettersResponseDto(
    val newsLetters: List<NewsLetter>
) {
    data class NewsLetter(
        val id: Int,
        val brandName: String,
        val imageUrl: String,
        val publicationCycle: String
    )
}
