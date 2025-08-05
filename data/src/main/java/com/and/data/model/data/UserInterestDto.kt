package com.and.data.model.data

import java.time.Instant

data class UserInterestDto(
    val userId: Int,
    val interestId: Int,
    val createdAt: Instant
)
