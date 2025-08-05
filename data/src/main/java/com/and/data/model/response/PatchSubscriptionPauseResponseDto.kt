package com.and.data.model.response

data class PatchSubscriptionPauseResponseDto(
    val userId: Int,
    val newsletterId: Int,
    val status: String
)
