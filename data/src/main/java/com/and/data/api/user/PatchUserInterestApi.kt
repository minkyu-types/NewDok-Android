package com.and.data.api.user

import com.and.data.model.request.PatchUserInterestRequestDto
import com.and.data.model.response.PatchUserInterestResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH

interface PatchUserInterestApi {

    @PATCH("/users/mypage/interest")
    fun patchUserInterest(
        @Body request: PatchUserInterestRequestDto
    ): Response<PatchUserInterestResponseDto>
}