package com.and.domain.repository

import com.and.domain.model.PopularKeywordsResult
import com.and.domain.model.SearchResult
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getNewsLetterSearchResult(keyword: String): Flow<SearchResult.SearchedNewsLetter>
    fun getArticleSearchResult(keyword: String): Flow<List<SearchResult.SearchedArticle>>

    fun getPopularKeywords(): Flow<PopularKeywordsResult>
}