package com.and.data.model.response

import com.squareup.moshi.Json

data class GetSubscribedNewsLettersResponseDto(
    @Json(name = "data") val newsLetters: List<BriefNewsLetterDto>
) {
    data class BriefNewsLetterDto(
        val id: Int,
        val brandName: String,
        val imageUrl: String,
        val publicationCycle: String
    )
}
