package com.and.data.api.user

import com.and.data.model.response.GetPreInvestigateNewLettersResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GetPreInvestigateNewsLettersApi {

    @GET("/users/preInvestigate")
    suspend fun getPreInvestigateNewsLetters(
        @Query("industry") industry: String,
        @Query("interest") interests: List<String>
    ): GetPreInvestigateNewLettersResponseDto
}