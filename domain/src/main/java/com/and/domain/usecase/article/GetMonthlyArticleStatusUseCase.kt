package com.and.domain.usecase.article

import com.and.domain.model.DailyArticleStatus
import com.and.domain.repository.ArticleRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.article.GetMonthlyArticleStatusUseCase.GetArticlesParams
import javax.inject.Inject

class GetMonthlyArticleStatusUseCase @Inject constructor(
    private val repository: ArticleRepository
): BaseSuspendUseCase<GetArticlesParams, List<DailyArticleStatus>> {

    override suspend fun invoke(parameter: GetArticlesParams): List<DailyArticleStatus> {
        return repository.getMonthlyArticleStatus(
            parameter.year,
            parameter.month
        )
    }

    data class GetArticlesParams(
        val year: Int,
        val month: Int
    )
}