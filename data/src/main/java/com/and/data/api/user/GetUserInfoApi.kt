package com.and.data.api.user

import com.and.data.model.data.UserInfoDto
import retrofit2.http.GET

interface GetUserInfoApi {

    @GET("/users/my")
    suspend fun getUserInfo(

    ): UserInfoDto
}