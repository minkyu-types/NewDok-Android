package com.and.domain.usecase.article

import com.and.domain.model.DailyArticles
import com.and.domain.repository.ArticleRepository
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.article.GetArticlesUseCase.GetArticlesParams

class GetArticlesUseCase(
    private val repository: ArticleRepository
): BaseUseCase<GetArticlesParams, List<DailyArticles>> {

    override suspend fun invoke(parameter: GetArticlesParams): List<DailyArticles> {
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