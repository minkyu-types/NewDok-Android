package com.and.data.model.response

import com.and.data.model.data.PopularKeywordDto

data class GetPopularKeywordsResponseDto(
    val updatedDate: String,
    val keywords: List<PopularKeywordDto>
)
