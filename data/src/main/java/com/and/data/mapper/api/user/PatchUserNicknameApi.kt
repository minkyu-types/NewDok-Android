package com.and.data.mapper.api.user

import com.and.data.mapper.model.request.PatchUserNicknameRequestDto
import retrofit2.Response
import retrofit2.http.Body

interface PatchUserNicknameApi {

    fun patchUserNickname(
        @Body request: PatchUserNicknameRequestDto
    ): Response<>
}