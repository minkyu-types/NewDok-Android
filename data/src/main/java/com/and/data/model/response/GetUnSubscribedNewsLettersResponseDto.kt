package com.and.data.model.response

data class GetUnSubscribedNewsLettersResponseDto(
    val newsLetters: List<GetSubscribedNewsLettersResponseDto.BriefNewsLetterDto>
)