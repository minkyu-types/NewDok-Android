package com.and.domain.usecase.article

import com.and.domain.model.type.InterestCategory
import com.and.domain.repository.ArticleRepository
import com.and.domain.usecase.BaseUseCase

class GetBookmarkedInterestsUseCase(
    private val repository: ArticleRepository
): BaseUseCase<Unit, List<InterestCategory>> {

    override suspend fun invoke(parameter: Unit): List<InterestCategory> {
        return repository.getBookmarkedInterests()
    }
}