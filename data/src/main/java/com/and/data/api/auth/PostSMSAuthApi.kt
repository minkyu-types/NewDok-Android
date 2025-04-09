package com.and.data.api.auth

import com.and.data.model.request.PostSMSAuthRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface PostSMSAuthApi {

    // TODO 응답 객체 작성
    @POST("/auth/SMS")
    fun postSMSAuth(
        @Body request: com.and.data.model.request.PostSMSAuthRequestDto
    )
}