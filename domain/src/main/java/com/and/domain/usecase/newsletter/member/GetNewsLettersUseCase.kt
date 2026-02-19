package com.and.domain.usecase.newsletter.member

import com.and.domain.model.NewsLetter
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.SortCategory
import com.and.domain.repository.MemberNewsLetterRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.newsletter.member.GetNewsLettersUseCase.GetNewsLettersParams
import java.time.Instant
import java.time.ZonedDateTime
import javax.inject.Inject
import javax.inject.Named

class GetNewsLettersUseCase @Inject constructor(
    @Named("member") private val repository: MemberNewsLetterRepository
): BaseSuspendUseCase<GetNewsLettersParams, List<NewsLetter>> {

    override suspend fun invoke(parameter: GetNewsLettersParams): List<NewsLetter> {
        return repository.getNewsLetters(
            parameter.orderOption,
            parameter.industry,
            parameter.dayId
        )
    }

    data class GetNewsLettersParams(
        val orderOption: SortCategory,
        val industry: IndustryCategory,
        val dayId: Int
    )
}