package com.and.domain.model

import java.time.Instant

data class Account(
    val id: Int,
    val loginId: String,
    val phoneNumber: String,
    val createdAt: Instant
)