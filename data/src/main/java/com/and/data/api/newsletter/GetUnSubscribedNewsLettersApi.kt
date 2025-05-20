package com.and.data.api.newsletter

import com.and.data.model.response.GetSubscribedNewsLettersResponseDto.BriefNewsLetterDto
import com.and.data.model.response.GetUnSubscribedNewsLettersResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface GetUnSubscribedNewsLettersApi {

    @GET("/newsletters/subscription/paused")
    suspend fun getUnSubscribedNewsLetters(

    ): List<BriefNewsLetterDto>
}