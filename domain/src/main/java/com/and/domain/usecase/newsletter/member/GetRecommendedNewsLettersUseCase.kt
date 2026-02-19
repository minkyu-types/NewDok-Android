package com.and.domain.usecase.newsletter.member

import com.and.domain.model.RecommendedNewsLetters
import com.and.domain.repository.MemberNewsLetterRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.BaseUseCase
import javax.inject.Inject
import javax.inject.Named

class GetRecommendedNewsLettersUseCase @Inject constructor(
    @Named("member") private val repository: MemberNewsLetterRepository
): BaseSuspendUseCase<Unit, RecommendedNewsLetters> {

    override suspend fun invoke(parameter: Unit): RecommendedNewsLetters {
        return repository.getRecommendedNewsLetters()
    }
}