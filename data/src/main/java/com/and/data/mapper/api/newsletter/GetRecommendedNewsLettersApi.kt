package com.and.data.mapper.api.newsletter

import com.and.data.mapper.model.response.GetRecommendedNewsLettersResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface GetRecommendedNewsLettersApi {

    @GET("/newsletters/recommend")
    fun getRecommendedNewsLetters(

    ): Response<GetRecommendedNewsLettersResponseDto>
}