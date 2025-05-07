package com.and.data.api.article

import com.and.data.model.response.GetBookmarkedInterestResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface GetBookmarkedInterestsApi {

    @GET("/articles/bookmark/interest")
    suspend fun getBookmarkedInterests(

    ): GetBookmarkedInterestResponseDto
}