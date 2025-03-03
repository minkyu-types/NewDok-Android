package com.and.data.mapper.api.user

import com.and.data.mapper.model.request.LoginRequestDto
import com.and.data.mapper.model.response.PostLoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PostLoginApi {

    @POST("/users/login")
    suspend fun login(
        @Body request: LoginRequestDto
    ): Response<PostLoginResponseDto>
}