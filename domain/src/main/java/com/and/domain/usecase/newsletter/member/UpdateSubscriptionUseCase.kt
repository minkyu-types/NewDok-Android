package com.and.domain.usecase.newsletter.member

import com.and.domain.repository.MemberNewsLetterRepository
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.newsletter.member.UpdateSubscriptionUseCase.UpdateSubscriptionParams

class UpdateSubscriptionUseCase(
    private val repository: MemberNewsLetterRepository
): BaseUseCase<UpdateSubscriptionParams, Boolean> {

    override suspend fun invoke(parameter: UpdateSubscriptionParams): Boolean {
        return repository.updateSubscription(
            parameter.newsLetterId
        )
    }

    data class UpdateSubscriptionParams(
        val newsLetterId: Int
    )
}