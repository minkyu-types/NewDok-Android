package com.and.data.api.user

import com.and.data.model.request.PatchUserNicknameRequestDto
import com.and.data.model.response.PatchUserNicknameResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface PatchUserNicknameApi {

    @GET("/users/mypage/nickname")
    fun patchUserNickname(
        @Body request: PatchUserNicknameRequestDto
    ): PatchUserNicknameResponseDto
}