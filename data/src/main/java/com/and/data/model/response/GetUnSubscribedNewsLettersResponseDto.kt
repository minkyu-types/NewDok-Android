package com.and.data.model.response

import com.squareup.moshi.Json

data class GetUnSubscribedNewsLettersResponseDto(
    @Json(name = "data") val newsLetters: List<GetSubscribedNewsLettersResponseDto.BriefNewsLetterDto>
)