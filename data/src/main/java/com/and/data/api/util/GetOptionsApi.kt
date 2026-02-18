package com.and.data.api.util

import com.and.data.model.response.GetOptionsResponseDto
import retrofit2.http.GET

/**
 * 산업군, 관심사, 요일 목록을 조회하는 API
 */
interface GetOptionsApi {

    @GET("/options")
    suspend fun getOptions(

    ): GetOptionsResponseDto
}