package com.and.data.mapper.api.article

import com.and.data.mapper.model.response.GetReadArticleResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GetReadArticleApi {

    @GET("/articles/{id}")
    fun getReadArticle(
        @Path("id") id: String
    ): Response<GetReadArticleResponseDto>
}