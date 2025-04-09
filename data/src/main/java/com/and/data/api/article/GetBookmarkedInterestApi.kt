package com.and.data.api.article

import com.and.data.model.response.GetBookmarkedInterestResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface GetBookmarkedInterestApi {

    @GET("/articles/bookmark/interest")
    fun getBookmarkedInterest(

    ): Response<com.and.data.model.response.GetBookmarkedInterestResponseDto>
}