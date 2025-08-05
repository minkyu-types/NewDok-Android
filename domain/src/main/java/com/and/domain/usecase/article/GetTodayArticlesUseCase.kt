package com.and.domain.usecase.article

import com.and.domain.model.Article
import com.and.domain.repository.ArticleRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.BaseUseCase

class GetTodayArticlesUseCase(
    private val repository: ArticleRepository
): BaseSuspendUseCase<Unit, List<Article>> {

    override suspend fun invoke(parameter: Unit): List<Article> {
        return repository.getTodayArticles()
    }
}