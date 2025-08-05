package com.and.data.api.newsletter

import com.and.data.model.response.GetRecommendedNewsLettersResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface GetRecommendedNewsLettersApi {

    @GET("/newsletters/recommend")
    suspend fun getRecommendedNewsLetters(

    ): GetRecommendedNewsLettersResponseDto
}