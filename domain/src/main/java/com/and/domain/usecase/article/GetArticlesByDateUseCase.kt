package com.and.domain.usecase.article

import com.and.domain.model.Article
import com.and.domain.repository.ArticleRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.article.GetArticleByIdUseCase.GetArticleByIdParams
import com.and.domain.usecase.article.GetArticlesByDateUseCase.GetArticlesByDateParams
import javax.inject.Inject

class GetArticlesByDateUseCase @Inject constructor(
    private val repository: ArticleRepository
): BaseSuspendUseCase<GetArticlesByDateParams, List<Article>> {

    override suspend fun invoke(parameter: GetArticlesByDateParams): List<Article> {
        return repository.getArticlesByDate(
            year = parameter.year,
            month = parameter.month,
            day = parameter.date
        )
    }

    data class GetArticlesByDateParams(
        val year: Int,
        val month: Int,
        val date: Int,
    )
}