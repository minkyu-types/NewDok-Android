package com.and.data.mapper.api.user

import com.and.data.mapper.model.request.PatchUserIndustryRequestDto
import com.and.data.mapper.model.response.PatchUserIndustryResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH

interface PatchUserIndustryApi {

    @PATCH("/users/mypage/industry")
    fun patchUserIndustry(
        @Body request: PatchUserIndustryRequestDto
    ): Response<PatchUserIndustryResponseDto>
}