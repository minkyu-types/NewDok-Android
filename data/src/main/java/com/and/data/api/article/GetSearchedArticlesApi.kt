package com.and.data.api.article

import com.and.data.model.response.GetSearchedArticlesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetSearchedArticlesApi {

    @GET("/articles/search")
    suspend fun getSearchedArticles(
        @Query("keyword") keyword: String
    ): GetSearchedArticlesResponseDto
}