package com.and.domain.usecase.article

import com.and.domain.model.MonthlyBookmarkedArticles
import com.and.domain.model.type.InterestCategory
import com.and.domain.repository.ArticleRepository
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.article.GetBookmarkedArticlesUseCase.GetBookmarkedArticlesParams

class GetBookmarkedArticlesUseCase(
    private val repository: ArticleRepository
): BaseUseCase<GetBookmarkedArticlesParams, MonthlyBookmarkedArticles> {

    override suspend fun invoke(parameter: GetBookmarkedArticlesParams): MonthlyBookmarkedArticles {
        return repository.getBookmarkedArticles(
            parameter.interest
        )
    }

    data class GetBookmarkedArticlesParams(
        val interest: InterestCategory
    )
}
