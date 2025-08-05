package com.and.data.api.search

import com.and.data.model.response.GetNewsLetterByNameResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GetArticleByKeywordApi {

    @GET("/search/article")
    suspend fun getNewsLetterByName(
        @Query("keyword") keyword: String
    ): GetNewsLetterByNameResponseDto
}