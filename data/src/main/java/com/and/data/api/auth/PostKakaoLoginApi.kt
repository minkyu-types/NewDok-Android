package com.and.data.api.auth

import com.and.data.model.request.KakaoLoginRequestDto
import com.and.data.model.response.KakaoLoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface PostKakaoLoginApi {

    @POST("/auth/kakao")
    suspend fun kakaoLogin(
        @Body request: KakaoLoginRequestDto
    ): KakaoLoginResponseDto
}
