package com.and.data.mapper.api.newsletter

import com.and.data.mapper.model.response.GetSubscribedNewsLettersResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface GetSubscribedNewsLettersApi {

    @GET("/newsletters/subscription/active")
    fun getSubscribedNewsLetters(

    ): Response<GetSubscribedNewsLettersResponseDto>
}