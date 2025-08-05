package com.and.data.model.response

import java.time.Instant

data class DeleteUserResponseDto(
    val message: String,
    val deletedAt: Instant
)
