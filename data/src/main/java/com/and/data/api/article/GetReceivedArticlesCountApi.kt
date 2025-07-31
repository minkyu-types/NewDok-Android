package com.and.data.api.article

import com.and.data.model.response.GetArticlesCountResponseDto
import retrofit2.http.GET

interface GetReceivedArticlesCountApi {

    @GET("/articles/received/count")
    suspend fun getArticles(

    ): GetArticlesCountResponseDto
}