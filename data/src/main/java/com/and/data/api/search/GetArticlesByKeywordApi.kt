package com.and.data.api.search

import com.and.data.model.response.GetSearchedArticlesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GetArticlesByKeywordApi {

    @GET("/search/article")
    suspend fun getArticlesByName(
        @Query("keyword") keyword: String
    ): GetSearchedArticlesResponseDto
}