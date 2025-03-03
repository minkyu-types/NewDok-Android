package com.and.data.mapper.model.request

data class PatchUserPasswordRequestDto(
    val loginId: String,
    val prevPassword: String,
    val password: String
)
