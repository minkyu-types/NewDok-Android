package com.and.domain.usecase.newsletter.member

import com.and.domain.model.RecommendedNewsLetter
import com.and.domain.repository.MemberNewsLetterRepository
import com.and.domain.usecase.BaseUseCase

class GetRecommendedNewsLetters(
    private val repository: MemberNewsLetterRepository
): BaseUseCase<Unit, List<RecommendedNewsLetter>> {

    override suspend fun invoke(parameter: Unit): List<RecommendedNewsLetter> {
        return repository.getRecommendedNewsLetters()
    }
}