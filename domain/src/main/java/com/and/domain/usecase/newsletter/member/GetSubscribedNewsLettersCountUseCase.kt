package com.and.domain.usecase.newsletter.member

import com.and.domain.repository.MemberNewsLetterRepository
import com.and.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class GetSubscribedNewsLettersCountUseCase @Inject constructor(
    private val repository: MemberNewsLetterRepository
): BaseSuspendUseCase<Unit, Int> {

    override suspend fun invoke(parameter: Unit): Int {
        return repository.getSubscribedNewsLettersCount()
    }
}