package com.and.data.api.article

import com.and.data.model.response.GetArticlesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GetArticlesApi {

    @GET("/articles")
    suspend fun getArticles(
        @Query("year") year: String,
        @Query("publicationMonth") month: String
    ): GetArticlesResponseDto
}