package com.and.domain.usecase.newsletter.nonmember

import com.and.domain.model.NewsLetter
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.SortCategory
import com.and.domain.repository.NonMemberNewsLetterRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.BaseUseCase
import java.time.Instant

class GetNewsLettersUseCase(
    private val repository: NonMemberNewsLetterRepository
): BaseSuspendUseCase<GetNewsLettersUseCase.GetNewsLettersParams, List<NewsLetter>> {

    override suspend fun invoke(parameter: GetNewsLettersParams): List<NewsLetter> {
        return repository.getNewsLetters(
            parameter.orderOption,
            parameter.industries,
            parameter.dates
        )
    }

    data class GetNewsLettersParams(
        val orderOption: SortCategory,
        val industries: List<IndustryCategory>,
        val dates: List<Instant>
    )
}