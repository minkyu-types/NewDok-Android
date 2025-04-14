package com.and.domain.usecase.article

import com.and.domain.model.Article
import com.and.domain.repository.ArticleRepository
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.article.SearchArticleByKeywordUseCase.SearchArticleByKeywordParams

class SearchArticleByKeywordUseCase(
    private val repository: ArticleRepository
): BaseUseCase<SearchArticleByKeywordParams, Article> {

    override suspend fun invoke(parameter: SearchArticleByKeywordParams): Article {
        return repository.searchArticle(
            parameter.keyword
        )
    }

    data class SearchArticleByKeywordParams(
        val keyword: String
    )
}
