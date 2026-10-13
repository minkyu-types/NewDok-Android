package com.and.data.api.search

import com.and.data.model.response.GetPopularKeywordsResponseDto
import retrofit2.http.GET

interface GetPopularKeywordsApi {

    @GET("search/popular")
    suspend fun getPopularKeywords(

    ): GetPopularKeywordsResponseDto
}