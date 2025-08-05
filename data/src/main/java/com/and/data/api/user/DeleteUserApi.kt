package com.and.data.api.user

import com.and.data.model.response.DeleteUserResponseDto
import retrofit2.http.PATCH

/**
 * 유저 회원 탈퇴 (Soft delete)
 */
interface DeleteUserApi {

    @PATCH("/users/withdraw")
    suspend fun deleteUser(

    ): DeleteUserResponseDto
}