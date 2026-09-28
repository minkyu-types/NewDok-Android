package com.and.data.model.request

data class KakaoLoginRequestDto(
    val provider: String,
    val platform: String,
    val idToken: String,
    val authorizationCode: String
)
