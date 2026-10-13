package com.and.presentation.model

import com.and.domain.model.PopularKeywordsResult

/**
 * 검색 화면의 인기 검색어.
 *
 * @param updatedDate 집계 기준일. 서버가 화면 표시용 문자열("11/21" 형식)로 내려준다
 * @param keywords 순위 순으로 정렬된 검색어. 순위는 목록의 순서로 표현한다
 */
data class PopularKeywordsModel(
    val updatedDate: String,
    val keywords: List<PopularKeywordModel>
) {
    companion object {
        val EMPTY = PopularKeywordsModel(
            updatedDate = "",
            keywords = emptyList()
        )
    }
}

data class PopularKeywordModel(
    val rank: Int,
    val keyword: String
)

fun PopularKeywordsResult.toPresentation() = PopularKeywordsModel(
    updatedDate = updatedDate,
    keywords = keywords.map { PopularKeywordModel(it.rank, it.keyword) }
)
