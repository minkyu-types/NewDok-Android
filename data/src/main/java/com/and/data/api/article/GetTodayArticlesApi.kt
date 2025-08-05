package com.and.data.api.article

import com.and.data.model.response.GetTodayArticlesResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface GetTodayArticlesApi {

    @GET("/articles/today")
    suspend fun getTodayArticles(

    ): GetTodayArticlesResponseDto
}