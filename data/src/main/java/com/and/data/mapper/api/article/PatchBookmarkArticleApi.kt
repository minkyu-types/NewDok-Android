package com.and.data.mapper.api.article

import com.and.data.mapper.model.request.PatchBookmarkArticleRequestDto
import com.and.data.mapper.model.response.PatchBookmarkArticleResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH

/**
 * 현재 북마크 중인 아티클은 취소하고, 북마크 중이 아닌 아티클은 요청
 *
 * @param request 아티클 Id
 */
interface PatchBookmarkArticleApi {

    @PATCH("/articles/bookmark")
    fun patchBookmarkArticle(
        @Body request: PatchBookmarkArticleRequestDto
    ): Response<PatchBookmarkArticleResponseDto>
}