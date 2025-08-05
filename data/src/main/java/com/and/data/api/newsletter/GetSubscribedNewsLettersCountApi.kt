package com.and.data.api.newsletter

import com.and.data.model.response.GetSubscribedNewsLettersCountResponseDto
import retrofit2.http.GET

interface GetSubscribedNewsLettersCountApi {

    @GET("/newsletters/subscription/count")
    suspend fun getSubscribedNewsLetters(

    ): GetSubscribedNewsLettersCountResponseDto
}