package com.and.data.model.response

data class ErrorResponseDto(
    val statusCode: Int,
    val message: String,
    val error: String
)
