package com.and.domain.usecase.user

import com.and.domain.model.NewsLetter
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.InterestCategory
import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.user.GetPreInvestigateNewsLettersUseCase.GetPreInvestigateNewsLettersParams
import javax.inject.Inject

class GetPreInvestigateNewsLettersUseCase @Inject constructor(
    private val repository: UserRepository
): BaseSuspendUseCase<GetPreInvestigateNewsLettersParams, List<NewsLetter>> {

    override suspend fun invoke(parameter: GetPreInvestigateNewsLettersParams): List<NewsLetter> {
        return repository.getPreInvestigateNewsLetters(
            parameter.industry,
            parameter.interests
        )
    }

    data class GetPreInvestigateNewsLettersParams(
        val industry: IndustryCategory,
        val interests: List<InterestCategory>
    )
}