package com.and.data.api.newsletter

import com.and.data.model.response.GetNewsLettersResponseDto
import com.and.data.model.response.GetNewsLettersResponseDto.NewsLetterDetailDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
* 유저(회원)가 선택한 산업군, 발행 요일, 정렬 기준에 따라 뉴스레터 브랜드 목록을 필터링
 */
interface GetMemberNewsLettersApi {

    @GET("/newsletters")
    suspend fun getAllNewsLetters(
        @Query("orderOpt") orderOpt: String,
        @Query("industry") industry: String,
        @Query("day") day: String,
    ): List<NewsLetterDetailDto>
}