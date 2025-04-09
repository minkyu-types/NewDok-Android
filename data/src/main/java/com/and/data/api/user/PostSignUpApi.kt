package com.and.data.api.user

import com.and.data.model.request.SignUpRequestDto
import com.and.data.model.response.PostSignUpResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PostSignUpApi {

    @POST("/users/signup")
    fun signUp(
        @Body request: SignUpRequestDto
    ): Response<PostSignUpResponseDto>
}