package com.and.domain.usecase.article

import com.and.domain.model.Article
import com.and.domain.repository.ArticleRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.article.SearchArticleByKeywordUseCase.SearchArticleByKeywordParams

class SearchArticleByKeywordUseCase(
    private val repository: ArticleRepository
): BaseSuspendUseCase<SearchArticleByKeywordParams, List<Article>> {

    override suspend fun invoke(parameter: SearchArticleByKeywordParams): List<Article> {
        return repository.searchArticles(
            parameter.keyword
        )
    }

    data class SearchArticleByKeywordParams(
        val keyword: String
    )
}
