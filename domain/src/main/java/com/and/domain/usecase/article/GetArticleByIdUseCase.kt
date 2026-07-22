package com.and.domain.usecase.article

import com.and.domain.model.ArticleDetail
import com.and.domain.repository.ArticleRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.article.GetArticleByIdUseCase.GetArticleByIdParams
import javax.inject.Inject

class GetArticleByIdUseCase @Inject constructor(
    private val repository: ArticleRepository
): BaseSuspendUseCase<GetArticleByIdParams, ArticleDetail> {

    override suspend fun invoke(parameter: GetArticleByIdParams): ArticleDetail {
        return repository.getArticleById(
            articleId = parameter.articleId
        )
    }

    data class GetArticleByIdParams(
        val articleId: Int
    )
}
