package com.and.data.mapper.api.article

import com.and.data.mapper.model.response.GetArticlesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetArticlesApi {

    @GET("/articles")
    fun getArticles(
        @Query("year") year: String,
        @Query("publicationMonth") publicationMonth: String
    ): Response<GetArticlesResponseDto>
}