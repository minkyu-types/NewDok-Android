package com.and.domain.usecase.newsletter.nonmember

import com.and.domain.model.NewsLetter
import com.and.domain.repository.NonMemberNewsLetterRepository
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.newsletter.nonmember.GetNewsLetterByIdUseCase.GetNewsLetterByIdParams

class GetNewsLetterByIdUseCase(
    private val repository: NonMemberNewsLetterRepository
): BaseUseCase<GetNewsLetterByIdParams, NewsLetter> {

    override suspend fun invoke(parameter: GetNewsLetterByIdParams): NewsLetter {
        return repository.getNewsLetterById(
            parameter.newsLetterId
        )
    }

    data class GetNewsLetterByIdParams(
        val newsLetterId: Int
    )
}