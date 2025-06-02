package com.and.data.api.user

import com.and.data.model.response.GetUserIdDuplicationResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GetUserIdDuplicationApi {

    @GET("/users/check/loginId")
    suspend fun getUserIdDuplication(
        @Query("loginId") loginId: String
    ): GetUserIdDuplicationResponseDto
}