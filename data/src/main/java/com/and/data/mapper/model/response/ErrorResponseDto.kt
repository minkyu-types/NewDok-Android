package com.and.data.mapper.model.response

data class ErrorResponseDto(
    val statusCode: Int,
    val message: String,
    val error: String
)
