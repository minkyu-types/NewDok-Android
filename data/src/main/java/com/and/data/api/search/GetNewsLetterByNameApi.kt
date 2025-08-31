package com.and.data.api.search

import com.and.data.model.response.GetSearchedNewsLetterResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GetNewsLetterByNameApi {

    @GET("/newsletters/brandName")
    suspend fun getNewsLetterByName(
        @Query("brandName") brandName: String
    ): GetSearchedNewsLetterResponseDto
}