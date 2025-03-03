package com.and.data.mapper.api.article

import retrofit2.http.GET
import retrofit2.http.Query

interface GetSearchedArticlesApi {

    // TODO 응답 객체 작성
    @GET("/articles/search")
    fun getSearchedArticles(
        @Query("keyword") keyword: String
    )
}