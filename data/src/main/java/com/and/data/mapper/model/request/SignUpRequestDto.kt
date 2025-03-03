package com.and.data.mapper.model.request

data class SignUpRequestDto(
    val logId: String,
    val password: String,
    val phoneNumber: String,
    val nickname: String,
    val birthYear: String,
    val gender: String
)
