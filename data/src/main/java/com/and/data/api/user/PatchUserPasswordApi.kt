package com.and.data.api.user

import com.and.data.model.request.PatchUserPasswordRequestDto
import com.and.data.model.response.PatchUserPasswordResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH

interface PatchUserPasswordApi {

    @PATCH("/users/mypage/password")
    suspend fun patchUserPassword(
        @Body request: PatchUserPasswordRequestDto
    ): Response<PatchUserPasswordResponseDto>
}