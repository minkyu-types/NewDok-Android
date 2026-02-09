package com.and.presentation.model

import com.and.domain.model.SearchResult
import java.time.Instant

sealed class SearchResultModel {

    data class MemberSearchResultModel(
        val message: String,
        val newsLetter: SearchedNewsLetterModel,
        val articles: List<SearchedArticleModel>
    ): SearchResultModel() {
        companion object {
            val EMPTY = MemberSearchResultModel(
                message = "",
                newsLetter = SearchedNewsLetterModel.EMPTY,
                articles = emptyList()
            )
        }
    }

    data class NonMemberSearchResultModel(
        val newsLetter: SearchedNewsLetterModel,
    ): SearchResultModel() {
        companion object {
            val EMPTY = NonMemberSearchResultModel(
                newsLetter = SearchedNewsLetterModel.EMPTY
            )
        }
    }

    data class SearchedNewsLetterModel(
        val id: Int,
        val brandName: String,
        val firstDescription: String? = null,
        val imageUrl: String?
    ) {
        companion object {
            val EMPTY = SearchedNewsLetterModel(
                id = -1,
                brandName = "",
                firstDescription = null,
                imageUrl = null
            )
        }
    }

    data class SearchedArticleModel(
        val id: Int,
        val title: String,
        val matchedText: String,
        val date: Instant,
        val newsLetter: SearchedNewsLetterModel,
        val matchType: String,
    ) {
        companion object {
            val EMPTY = SearchedArticleModel(
                id = -1,
                title = "",
                matchedText = "",
                date = Instant.EPOCH,
                newsLetter = SearchedNewsLetterModel.EMPTY,
                matchType = ""
            )
        }
    }
}

fun SearchResult.SearchedNewsLetter.toPresentation() = SearchResultModel.SearchedNewsLetterModel(
    id = id,
    brandName = brandName,
    firstDescription = firstDescription,
    imageUrl = imageUrl
)

fun SearchResult.SearchedArticle.toPresentation() = SearchResultModel.SearchedArticleModel(
    id = id,
    title = title,
    matchedText = matchedText,
    date = date,
    newsLetter = newsLetter.toPresentation(),
    matchType = matchType
)