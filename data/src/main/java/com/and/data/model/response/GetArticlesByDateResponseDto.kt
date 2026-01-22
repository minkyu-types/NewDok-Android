package com.and.data.model.response

import com.and.data.model.data.NewsLetterDto

data class GetArticlesByDateResponseDto(
    val id: Int,
    val title: String,
    val publishDate: Int,
    val status: String,
    val newsletter: NewsLetterDto
)