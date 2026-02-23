package com.and.domain.usecase.newsletter.member

import com.and.domain.model.NewsLetter
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.SortCategory
import com.and.domain.repository.MemberNewsLetterRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.newsletter.member.GetNewsLettersUseCase.GetNewsLettersParams
import javax.inject.Inject

class GetNewsLettersUseCase @Inject constructor(
    private val repository: MemberNewsLetterRepository
): BaseSuspendUseCase<GetNewsLettersParams, List<NewsLetter>> {

    override suspend fun invoke(parameter: GetNewsLettersParams): List<NewsLetter> {
        return repository.getNewsLetters(
            parameter.orderOption,
            parameter.industries,
            parameter.dayIds
        )
    }

    data class GetNewsLettersParams(
        val orderOption: SortCategory,
        val industries: List<IndustryCategory>,
        val dayIds: List<Int>
    )
}
