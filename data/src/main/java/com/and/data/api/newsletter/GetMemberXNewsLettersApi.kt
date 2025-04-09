package com.and.data.api.newsletter

import retrofit2.http.GET
import retrofit2.http.Query

/**
* 유저(!!!비회원!!!)가 선택한 산업군, 발행 요일, 정렬 기준에 따라 뉴스레터 브랜드 목록을 필터링
 */
interface GetMemberXNewsLettersApi {

    // TODO 응답 객체 작성
    @GET("/newsletters/non-member")
    fun getNewsLetters(
        @Query("orderOpt") orderOpt: String,
        @Query("industry") industry: List<String>,
        @Query("day") day: List<String>,
    )
}