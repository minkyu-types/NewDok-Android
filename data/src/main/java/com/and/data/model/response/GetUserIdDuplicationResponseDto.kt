package com.and.data.model.response

import java.time.Instant

data class GetUserIdDuplicationResponseDto(
    val id: Int,
    val loginId: String,
    val phoneNumber: String,
    val createdAt: Instant
)
