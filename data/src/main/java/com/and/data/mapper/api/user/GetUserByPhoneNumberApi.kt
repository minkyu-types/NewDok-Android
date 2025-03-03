package com.and.data.mapper.api.user

import com.and.data.mapper.model.response.GetUserByPhoneNumberResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetUserByPhoneNumberApi {

    @GET("/users/check/phoneNumber")
    fun getUserByPhoneNumber(
        @Query("phoneNumber") phoneNumber: String
    ): Response<GetUserByPhoneNumberResponseDto>
}