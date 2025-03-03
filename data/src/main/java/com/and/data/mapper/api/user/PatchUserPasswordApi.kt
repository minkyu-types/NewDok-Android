package com.and.data.mapper.api.user

import com.and.data.mapper.model.request.PatchUserPasswordRequestDto
import com.and.data.mapper.model.response.PatchUserPasswordResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH

interface PatchUserPasswordApi {

    @PATCH("/users/mypage/password")
    fun patchUserPassword(
        @Body request: PatchUserPasswordRequestDto
    ): Response<PatchUserPasswordResponseDto>
}