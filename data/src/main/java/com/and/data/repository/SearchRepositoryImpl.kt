package com.and.data.repository

import com.and.data.api.search.GetArticlesByKeywordApi
import com.and.data.api.search.GetNewsLetterByNameApi
import com.and.data.api.search.GetPopularKeywordsApi
import com.and.data.model.data.toDomain
import com.and.data.model.response.toDomain
import com.and.domain.model.PopularKeywordsResult
import com.and.domain.model.SearchResult
import com.and.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val getSearchedNewsLettersApi: GetNewsLetterByNameApi,
    private val getSearchedArticlesApi: GetArticlesByKeywordApi,
    private val getPopularKeywordsApi: GetPopularKeywordsApi
): SearchRepository, BaseRepository() {
    override fun getNewsLetterSearchResult(keyword: String): Flow<SearchResult.SearchedNewsLetter> {
        return handleApiFlow(
            apiCall = {
                val newsLetterResult = getSearchedNewsLettersApi.getNewsLetterByName(keyword)
                val articlesResult = getSearchedArticlesApi.getArticlesByName(keyword)

                Pair(newsLetterResult, articlesResult)
            },
            mapper = { response ->
                val newsLetter = response.first.toDomain()

                SearchResult.SearchedNewsLetter(
                    id = newsLetter.id,
                    brandName = newsLetter.brandName,
                    firstDescription = newsLetter.firstDescription,
                    imageUrl = newsLetter.imageUrl
                )
            }
        )
    }

    override fun getArticleSearchResult(keyword: String): Flow<List<SearchResult.SearchedArticle>> {
        return handleApiFlow(
            apiCall = {
                getSearchedArticlesApi.getArticlesByName(keyword).results
            },
            mapper = { response ->
                response?.map { it.toDomain() } ?: emptyList()
            }
        )
    }

    override fun getPopularKeywords(): Flow<PopularKeywordsResult> {
        return handleApiFlow(
            apiCall = {
                getPopularKeywordsApi.getPopularKeywords();
            },
            mapper = { response ->
                PopularKeywordsResult(
                    updatedDate = response.updatedDate,
                    keywords = response.keywords.map { it.toDomain() }
                )
            }
        )
    }
}