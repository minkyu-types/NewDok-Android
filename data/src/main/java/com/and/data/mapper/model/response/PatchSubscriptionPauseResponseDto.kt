package com.and.data.mapper.model.response

data class PatchSubscriptionPauseResponseDto(
    val userId: Int,
    val newsletterId: Int,
    val status: String
)
