package com.and.domain.usecase.newsletter.member

import com.and.domain.model.NewsLetter
import com.and.domain.repository.MemberNewsLetterRepository
import com.and.domain.usecase.BaseUseCase

class GetSubscribedNewsLettersUseCase(
    private val repository: MemberNewsLetterRepository
): BaseUseCase<Unit, List<NewsLetter>> {

    override suspend fun invoke(parameter: Unit): List<NewsLetter> {
        return repository.getSubscribedNewsLetters()
    }
}