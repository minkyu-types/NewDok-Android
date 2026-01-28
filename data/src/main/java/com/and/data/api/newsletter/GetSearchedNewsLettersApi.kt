package com.and.data.api.newsletter

import com.and.data.model.response.GetNewsLettersResponseDto.NewsLetterDetailDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GetSearchedNewsLettersApi {

    @GET("/newsletters/search")
    suspend fun getSearchedNewsLetters(
        @Query("brandName") brandName: String
    ): NewsLetterDetailDto
}