package com.and.data.api.article

import com.and.data.model.response.GetReadArticleResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 특정 id 값을 기반으로 아티클 조회
 *
 * @param interest 관심사 Id
 */
interface GetArticleByIdApi {

    @GET("/articles/{id}")
    fun getReadArticle(
        @Path("id") articleId: String
    ): Response<GetReadArticleResponseDto>
}