package com.and.data.model.data

data class KakaoProfileDto(
    val provider: String,
    val providerUserId: String,
    val email: String?,
    val nickname: String?
)
