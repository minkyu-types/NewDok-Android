package com.and.data.api.user

import com.and.data.model.response.GetUserByPhoneNumberResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GetUserByPhoneNumberApi {

    @GET("/users/check/phoneNumber")
    suspend fun getUserByPhoneNumber(
        @Query("phoneNumber") phoneNumber: String
    ): GetUserByPhoneNumberResponseDto
}