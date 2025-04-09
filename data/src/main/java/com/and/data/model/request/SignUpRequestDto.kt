package com.and.data.model.request

data class SignUpRequestDto(
    val loginId: String,
    val password: String,
    val phoneNumber: String,
    val nickname: String,
    val birthYear: String,
    val gender: String
)
