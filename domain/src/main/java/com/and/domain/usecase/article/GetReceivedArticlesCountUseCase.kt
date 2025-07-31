package com.and.domain.usecase.article

import com.and.domain.repository.ArticleRepository
import com.and.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class GetReceivedArticlesCountUseCase @Inject constructor(
    private val repository: ArticleRepository
): BaseSuspendUseCase<Unit, Int> {

    override suspend fun invoke(parameter: Unit): Int {
        return repository.getReceivedArticlesCount()
    }
}