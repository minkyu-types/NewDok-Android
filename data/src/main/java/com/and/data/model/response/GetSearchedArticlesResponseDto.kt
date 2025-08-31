package com.and.data.model.response

import com.and.data.model.data.NewsLetterDto
import com.and.domain.model.SearchResult
import java.time.Instant

data class GetSearchedArticlesResponseDto(
    val message: String,
    val results: List<ArticleSearchResultDto>? = emptyList(),
    val totalCount: Int,
)

data class ArticleSearchResultDto(
    val id: Int,
    val title: String,
    val matchedText: String,
    val date: Instant,
    val newsLetters: List<NewsLetterDto>,
    val matchType: String,
)

fun ArticleSearchResultDto.toDomain() = SearchResult.SearchedArticle(
    id = id,
    title = title,
    matchedText = matchedText,
    date = date,
    newsLetter = SearchResult.SearchedNewsLetter(
        id = newsLetters.firstOrNull()?.brandId ?: -1,
        brandName = newsLetters.firstOrNull()?.brandName.orEmpty(),
        firstDescription = newsLetters.firstOrNull()?.briefDescription,
        imageUrl = newsLetters.firstOrNull()?.imageUrl,
    ),
    matchType = matchType,
)