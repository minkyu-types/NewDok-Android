package com.and.data.api.article

import com.and.data.model.request.PatchBookmarkArticleRequestDto
import com.and.data.model.response.PatchBookmarkArticleResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 현재 북마크 중인 아티클은 취소하고, 북마크 중이 아닌 아티클은 요청
 *
 * @param request 아티클 Id
 */
interface PatchBookmarkArticleApi {

    @POST("/articles/bookmark")
    suspend fun postBookmarkArticle(
        @Body request: PatchBookmarkArticleRequestDto
    ): PatchBookmarkArticleResponseDto
}