package com.and.data.api.article

import com.and.data.model.response.GetReadArticleResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GetReadArticleApi {

    @GET("/articles/{id}")
    fun getReadArticle(
        @Path("id") id: String
    ): Response<com.and.data.model.response.GetReadArticleResponseDto>
}