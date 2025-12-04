package com.and.domain.usecase.article

import com.and.domain.model.DailyArticle
import com.and.domain.repository.ArticleRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.article.GetArticlesUseCase.GetArticlesParams
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repository: ArticleRepository
): BaseSuspendUseCase<GetArticlesParams, List<DailyArticle>> {

    override suspend fun invoke(parameter: GetArticlesParams): List<DailyArticle> {
        return repository.getArticles(
            parameter.year,
            parameter.month
        )
    }

    data class GetArticlesParams(
        val year: Int,
        val month: Int
    )
}