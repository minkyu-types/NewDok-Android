package com.and.data.api.auth

import com.and.data.model.request.KakaoSignupRequestDto
import com.and.data.model.response.KakaoSignupResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface PostKakaoSignupApi {

    @POST("/auth/kakao/signup")
    suspend fun kakaoSignup(
        @Body request: KakaoSignupRequestDto
    ): KakaoSignupResponseDto
}
