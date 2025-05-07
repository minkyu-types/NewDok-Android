package com.and.data.model.response

import com.and.data.model.data.UserInterestDto

data class PatchUserInterestResponseDto(
    val id: Int,
    val loginId: String,
    val interests: List<UserInterestDto>
)
