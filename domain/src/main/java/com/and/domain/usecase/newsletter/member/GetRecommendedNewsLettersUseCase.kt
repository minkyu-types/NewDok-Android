package com.and.domain.usecase.newsletter.member

import com.and.domain.model.RecommendedNewsLetters
import com.and.domain.repository.MemberNewsLetterRepository
import com.and.domain.usecase.BaseUseCase
import javax.inject.Inject

class GetRecommendedNewsLettersUseCase @Inject constructor(
    private val repository: MemberNewsLetterRepository
): BaseUseCase<Unit, RecommendedNewsLetters> {

    override suspend fun invoke(parameter: Unit): RecommendedNewsLetters {
        return repository.getRecommendedNewsLetters()
    }
}