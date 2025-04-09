package com.and.data.api.user

import com.and.data.model.response.GetPreInvestigateNewLettersResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetPreInvestigateNewsLettersApi {

    @GET("/users/preInvestigate")
    fun getPreInvestigateNewsLetters(
        @Query("industry") industry: String,
        @Query("interest") interest: List<String>
    ): Response<com.and.data.model.response.GetPreInvestigateNewLettersResponseDto>
}