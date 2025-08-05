package com.and.data.api.newsletter

import com.and.data.model.response.GetSubscribedNewsLettersResponseDto.BriefNewsLetterDto
import retrofit2.http.GET

interface GetSubscribedNewsLettersApi {

    @GET("/newsletters/subscription/active")
    suspend fun getSubscribedNewsLetters(

    ): List<BriefNewsLetterDto>
}