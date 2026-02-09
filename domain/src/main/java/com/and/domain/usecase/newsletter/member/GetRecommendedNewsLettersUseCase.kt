package com.and.domain.usecase.newsletter.member

import com.and.domain.model.RecommendedNewsLetter
import com.and.domain.model.type.RecommendedNewsLetterType
import com.and.domain.repository.MemberNewsLetterRepository
import com.and.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class GetRecommendedNewsLettersUseCase @Inject constructor(
    private val repository: MemberNewsLetterRepository
): BaseSuspendUseCase<RecommendedNewsLetterType, List<RecommendedNewsLetter>> {

    override suspend fun invoke(type: RecommendedNewsLetterType): List<RecommendedNewsLetter> {
        return repository.getRecommendedNewsLetters(type)
    }
}