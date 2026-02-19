package com.and.domain.usecase.newsletter.member

import com.and.domain.model.BriefNewsLetter
import com.and.domain.model.NewsLetter
import com.and.domain.repository.MemberNewsLetterRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.BaseUseCase
import javax.inject.Inject
import javax.inject.Named

class GetSubscribedNewsLettersUseCase @Inject constructor(
    @Named("member") private val repository: MemberNewsLetterRepository
): BaseSuspendUseCase<Unit, List<BriefNewsLetter>> {

    override suspend fun invoke(parameter: Unit): List<BriefNewsLetter> {
        return repository.getSubscribedNewsLetters()
    }
}