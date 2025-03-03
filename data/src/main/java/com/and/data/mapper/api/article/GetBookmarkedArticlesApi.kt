package com.and.data.mapper.api.article

import com.and.data.mapper.model.response.GetBookmarkedArticlesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 사용자가 북마크한 아티클을 관심사별로 조회
 *
 * @param interest 관심사 Id
 */
interface GetBookmarkedArticlesApi {

    @GET("/articles/bookmark")
    fun getBookmarkedArticles(
        @Query("interest") interest: String
    ): Response<GetBookmarkedArticlesResponseDto>
}