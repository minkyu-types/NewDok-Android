package com.and.data.mapper.api.article

import com.and.data.mapper.model.response.GetBookmarkedInterestResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface GetBookmarkedInterestApi {

    @GET("/articles/bookmark/interest")
    fun getBookmarkedInterest(

    ): Response<GetBookmarkedInterestResponseDto>
}