package com.and.data.mapper.model.data

import java.time.Instant

data class InterestDto(
    val userId: Int,
    val interestId: Int,
    val createdAt: Instant
)
