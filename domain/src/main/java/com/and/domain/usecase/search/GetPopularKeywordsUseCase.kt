package com.and.domain.usecase.search

import com.and.domain.model.PopularKeywordResult
import com.and.domain.repository.SearchRepository
import com.and.domain.usecase.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularKeywordsUseCase @Inject constructor(
    private val repository: SearchRepository
): BaseFlowUseCase<Unit, PopularKeywordResult> {
    override fun invoke(parameter: Unit): Flow<PopularKeywordResult> {
        return repository.getPopularKeywords()
    }
}
