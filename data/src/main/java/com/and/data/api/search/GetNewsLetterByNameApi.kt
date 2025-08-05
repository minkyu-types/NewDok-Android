package com.and.data.api.search

import com.and.data.model.response.GetNewsLetterByNameResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetNewsLetterByNameApi {

    @GET("/newsletters/brandName")
    suspend fun getNewsLetterByName(
        @Query("brandName") brandName: String
    ): GetNewsLetterByNameResponseDto
}