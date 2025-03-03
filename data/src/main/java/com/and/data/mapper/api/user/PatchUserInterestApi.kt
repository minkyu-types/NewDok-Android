package com.and.data.mapper.api.user

import com.and.data.mapper.model.request.PatchUserInterestRequestDto
import com.and.data.mapper.model.response.PatchUserInterestResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH

interface PatchUserInterestApi {

    @PATCH("/users/mypage/interest")
    fun patchUserInterest(
        @Body request: PatchUserInterestRequestDto
    ): Response<PatchUserInterestResponseDto>
}