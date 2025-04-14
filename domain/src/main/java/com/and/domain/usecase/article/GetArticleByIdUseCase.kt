package com.and.domain.usecase.article

import com.and.domain.model.Article
import com.and.domain.repository.ArticleRepository
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.article.GetArticleByIdUseCase.GetArticleByIdParams

class GetArticleByIdUseCase(
    private val repository: ArticleRepository
): BaseUseCase<GetArticleByIdParams, Article> {

    override suspend fun invoke(parameter: GetArticleByIdParams): Article {
        return repository.getArticleById(
            parameter.articleId
        )
    }

    data class GetArticleByIdParams(
        val articleId: Int
    )
}