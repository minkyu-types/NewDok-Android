package com.and.data.mapper.api.newsletter

import com.and.data.mapper.model.response.GetUnSubscribedNewsLettersResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface GetUnSubscribedNewsLettersApi {

    @GET("/newsletters/subscription/paused")
    fun getUnSubscribedNewsLetters(

    ): Response<GetUnSubscribedNewsLettersResponseDto>
}