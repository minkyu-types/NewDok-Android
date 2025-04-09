package com.and.data.api.user

import com.and.data.model.response.GetUserIdDuplicationResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetUserIdDuplicationApi {

    @GET("/users/check/loginId")
    fun getUserIdDuplication(
        @Query("loginId") loginId: String
    ): Response<com.and.data.model.response.GetUserIdDuplicationResponseDto>
}