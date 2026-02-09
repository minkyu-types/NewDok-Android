package com.and.data.api.newsletter

import com.and.data.model.response.GetRecommendedNewsLettersResponseDto
import com.and.data.model.response.GetRecommendedNewsLettersResponseDto.RecommendedNewsLetterDto
import retrofit2.Response
import retrofit2.http.GET

interface GetRecommendedNewsLettersApi {

    // 교집합
    @GET("/newsletters/recommend/intersection")
    suspend fun getIntersectionNewsLetters(

    ): List<RecommendedNewsLetterDto>

    // 합집합
    @GET("/newsletters/recommend/union")
    suspend fun getUnionNewsLetters(

    ): List<RecommendedNewsLetterDto>
}