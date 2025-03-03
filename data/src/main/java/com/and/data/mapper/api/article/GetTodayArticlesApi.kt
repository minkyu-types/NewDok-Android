package com.and.data.mapper.api.article

import com.and.data.mapper.model.response.GetTodayArticlesResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface GetTodayArticlesApi {

    @GET("/articles/today")
    fun getTodayArticles(

    ): Response<GetTodayArticlesResponseDto>
}