package com.and.data.api.auth

import com.and.data.model.request.PostSMSAuthRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PostSMSAuthApi {

    @POST("/auth/SMS")
    suspend fun postSMSAuth(
        @Body request: PostSMSAuthRequestDto
    ): String
}