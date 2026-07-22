package com.and.domain.model

data class KakaoProfile(
    val provider: String,
    val providerUserId: String,
    val email: String?,
    val nickname: String?
)
