package com.and.data.api.user

import com.and.data.model.request.PatchUserPhoneNumberRequestDto
import com.and.data.model.response.PatchUserPhoneNumberResponseDto
import retrofit2.http.Body
import retrofit2.http.PATCH

interface PatchUserPhoneNumberApi {

    @PATCH("/users/mypage/phoneNumber")
    fun patchUserPhoneNumber(
        @Body request: PatchUserPhoneNumberRequestDto
    ): PatchUserPhoneNumberResponseDto
}