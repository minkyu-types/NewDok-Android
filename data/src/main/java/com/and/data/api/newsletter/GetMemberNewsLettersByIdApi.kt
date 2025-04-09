package com.and.data.api.newsletter

import com.and.data.model.response.GetNewsLettersByIdResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * (회원용) 주어진 id 값에 해당하는 뉴스레터 브랜드의 상세 정보를 조회
 *
 * @param id
 */
interface GetMemberNewsLettersByIdApi {

    @GET("/newsletters/{id}")
    fun getNewsLettersById(
        @Path("id") id: String
    ): Response<com.and.data.model.response.GetNewsLettersByIdResponseDto>
}