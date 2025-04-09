package com.and.data.api.user

import com.and.data.model.request.PatchUserIndustryRequestDto
import com.and.data.model.response.PatchUserIndustryResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH

interface PatchUserIndustryApi {

    @PATCH("/users/mypage/industry")
    fun patchUserIndustry(
        @Body request: PatchUserIndustryRequestDto
    ): Response<PatchUserIndustryResponseDto>
}