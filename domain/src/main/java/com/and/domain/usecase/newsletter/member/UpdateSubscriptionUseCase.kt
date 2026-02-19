package com.and.domain.usecase.newsletter.member

import com.and.domain.repository.MemberNewsLetterRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.newsletter.member.UpdateSubscriptionUseCase.UpdateSubscriptionParams
import javax.inject.Inject
import javax.inject.Named

class UpdateSubscriptionUseCase @Inject constructor(
    @Named("member") private val repository: MemberNewsLetterRepository
): BaseSuspendUseCase<UpdateSubscriptionParams, Unit> {

    override suspend fun invoke(parameter: UpdateSubscriptionParams) {
        return repository.updateSubscription(
            parameter.newsLetterId,
            parameter.wasSubscribed
        )
    }

    data class UpdateSubscriptionParams(
        val newsLetterId: Int,
        val wasSubscribed: Boolean
    )
}