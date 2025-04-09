package com.and.data.model.request

data class PatchUserPasswordRequestDto(
    val loginId: String,
    val prevPassword: String,
    val password: String
)
