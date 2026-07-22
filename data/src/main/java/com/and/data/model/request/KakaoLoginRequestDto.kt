package com.and.data.model.request

data class KakaoLoginRequestDto(
    val code: String,
    val redirectUri: String
)
