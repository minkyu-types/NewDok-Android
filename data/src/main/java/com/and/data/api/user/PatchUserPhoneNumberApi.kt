package com.and.data.api.user

import com.and.data.model.request.PatchUserPhoneNumberRequestDto
import com.and.data.model.response.PatchUserPhoneNumberResponseDto
import retrofit2.Response
import retrofit2.http.Body

interface PatchUserPhoneNumberApi {

    fun patchUserPhoneNumber(
        @Body request: PatchUserPhoneNumberRequestDto
    ): Response<PatchUserPhoneNumberResponseDto>
}