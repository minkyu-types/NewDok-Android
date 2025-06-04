package com.and.data.model.data

import java.time.Instant

data class UserRegisterDto(
    val id: Int,
    val loginId: String,
    val phoneNumber: String,
    val nickname: String,
    val birthYear: String,
    val gender: String,
    val createdAt: Instant,
)
