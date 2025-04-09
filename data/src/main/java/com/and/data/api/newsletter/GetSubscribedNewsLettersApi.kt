package com.and.data.api.newsletter

import com.and.data.model.response.GetSubscribedNewsLettersResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface GetSubscribedNewsLettersApi {

    @GET("/newsletters/subscription/active")
    fun getSubscribedNewsLetters(

    ): Response<com.and.data.model.response.GetSubscribedNewsLettersResponseDto>
}