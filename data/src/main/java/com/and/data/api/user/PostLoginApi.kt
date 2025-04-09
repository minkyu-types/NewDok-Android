package com.and.data.api.user

import com.and.data.model.request.LoginRequestDto
import com.and.data.model.response.PostLoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PostLoginApi {

    @POST("/users/login")
    suspend fun login(
        @Body request: com.and.data.model.request.LoginRequestDto
    ): Response<com.and.data.model.response.PostLoginResponseDto>
}