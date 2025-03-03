package com.and.data.mapper.model.response

import com.and.data.mapper.model.data.InterestDto

data class PatchUserInterestResponseDto(
    val id: Int,
    val loginId: String,
    val interests: List<InterestDto>
)
