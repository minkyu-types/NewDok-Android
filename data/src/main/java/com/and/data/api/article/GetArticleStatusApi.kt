package com.and.data.api.article

import com.and.data.model.response.GetMonthlyArticleStatusResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GetArticleStatusApi {

    @GET("/articles")
    suspend fun getArticles(
        @Query("year") year: String,
        @Query("publicationMonth") month: String
    ): GetMonthlyArticleStatusResponseDto
}