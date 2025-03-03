package com.and.data.mapper.api.user

import com.and.data.mapper.model.request.PatchUserPhoneNumberRequestDto
import com.and.data.mapper.model.response.PatchUserPhoneNumberResponseDto
import retrofit2.Response
import retrofit2.http.Body

interface PatchUserPhoneNumberApi {

    fun patchUserPhoneNumber(
        @Body request: PatchUserPhoneNumberRequestDto
    ): Response<PatchUserPhoneNumberResponseDto>
}