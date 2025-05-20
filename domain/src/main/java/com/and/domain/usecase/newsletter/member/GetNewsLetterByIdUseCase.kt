package com.and.domain.usecase.newsletter.member

import com.and.domain.model.NewsLetter
import com.and.domain.repository.MemberNewsLetterRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.newsletter.member.GetNewsLetterByIdUseCase.GetNewsLetterByIdParams
import javax.inject.Inject

class GetNewsLetterByIdUseCase @Inject constructor(
    private val repository: MemberNewsLetterRepository
): BaseSuspendUseCase<GetNewsLetterByIdParams, NewsLetter> {

    override suspend fun invoke(parameter: GetNewsLetterByIdParams): NewsLetter {
        return repository.getNewsLetterById(
            parameter.newsLetterId
        )
    }

    data class GetNewsLetterByIdParams(
        val newsLetterId: Int
    )
}