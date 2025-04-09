package com.and.data.api.newsletter

import retrofit2.http.GET
import retrofit2.http.Query

interface GetSearchedNewsLettersApi {

    // TODO 응답 객체 작성
    @GET("/newsletters/search")
    fun getSearchedNewsLetters(
        @Query("brandName") brandName: String
    )
}