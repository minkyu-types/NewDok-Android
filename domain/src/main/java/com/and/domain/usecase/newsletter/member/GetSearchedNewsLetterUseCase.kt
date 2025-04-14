package com.and.domain.usecase.newsletter.member

import com.and.domain.model.NewsLetter
import com.and.domain.repository.MemberNewsLetterRepository
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.newsletter.member.GetSearchedNewsLetterUseCase.GetSearchedNewsLetterParams

class GetSearchedNewsLetterUseCase(
    private val repository: MemberNewsLetterRepository
) : BaseUseCase<GetSearchedNewsLetterParams, NewsLetter> {

    override suspend fun invoke(parameter: GetSearchedNewsLetterParams): NewsLetter {
        return repository.getSearchedNewsLetter(
            parameter.brandName
        )
    }

    data class GetSearchedNewsLetterParams(
        val brandName: String
    )
}