package com.and.data.model.response

data class GetSubscribedNewsLettersResponseDto(
    val newsLetters: List<BriefNewsLetterDto>
) {
    data class BriefNewsLetterDto(
        val id: Int,
        val brandName: String,
        val imageUrl: String,
        val publicationCycle: String
    )
}
