package com.and.data.mapper.api.newsletter

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * (비회원용) 주어진 id 값에 해당하는 뉴스레터 브랜드의 상세 정보를 조회
 *
 * @param id 브랜드 Id 값
 */
interface GetMemberXNewsLettersByIdApi {

    // TODO 응답 객체 작성
    @GET("/newsletters/{id}/non-member")
    fun getNewsLettersById(
        @Path("id") id: String
    )
}