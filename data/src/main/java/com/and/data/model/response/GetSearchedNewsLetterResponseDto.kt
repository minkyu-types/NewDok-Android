package com.and.data.model.response

import com.and.domain.model.SearchResult

data class GetSearchedNewsLetterResponseDto(
    val id: Int,
    val brandName: String,
    val firstDescription: String,
    val imageUrl: String?
)

fun GetSearchedNewsLetterResponseDto.toDomain() = SearchResult.SearchedNewsLetter(
    id = this.id,
    brandName = this.brandName,
    firstDescription = this.firstDescription,
    imageUrl = this.imageUrl
)