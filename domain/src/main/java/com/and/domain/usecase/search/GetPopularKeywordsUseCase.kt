package com.and.domain.usecase.search

import com.and.domain.model.PopularKeywordsResult
import com.and.domain.repository.SearchRepository
import com.and.domain.usecase.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularKeywordsUseCase @Inject constructor(
    private val repository: SearchRepository
): BaseFlowUseCase<Unit, PopularKeywordsResult> {
    override fun invoke(parameter: Unit): Flow<PopularKeywordsResult> {
        return repository.getPopularKeywords()
    }
}
