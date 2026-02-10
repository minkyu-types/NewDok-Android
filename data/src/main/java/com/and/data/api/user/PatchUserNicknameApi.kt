package com.and.data.api.user

import com.and.data.model.request.PatchUserNicknameRequestDto
import com.and.data.model.response.PatchUserNicknameResponseDto
import retrofit2.http.Body
import retrofit2.http.PATCH

interface PatchUserNicknameApi {

    @PATCH("/users/mypage/nickname")
    suspend fun patchUserNickname(
        @Body request: PatchUserNicknameRequestDto
    ): PatchUserNicknameResponseDto
}