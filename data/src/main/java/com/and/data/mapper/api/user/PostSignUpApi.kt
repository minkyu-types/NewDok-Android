package com.and.data.mapper.api.user

import com.and.data.mapper.model.request.SignUpRequestDto
import com.and.data.mapper.model.response.PostSignUpResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PostSignUpApi {

    @POST("/users/signup")
    fun signUp(
        @Body request: SignUpRequestDto
    ): Response<PostSignUpResponseDto>
}