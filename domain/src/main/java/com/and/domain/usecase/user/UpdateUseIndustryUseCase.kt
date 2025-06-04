package com.and.domain.usecase.user

import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.user.UpdateUserIndustryUseCase.UpdateUserIndustryParams
import javax.inject.Inject

class UpdateUserIndustryUseCase @Inject constructor(
    private val repository: UserRepository
): BaseSuspendUseCase<UpdateUserIndustryParams, Unit> {

    override suspend fun invoke(parameter: UpdateUserIndustryParams) {
        return repository.updateUserIndustry(
            parameter.industryId
        )
    }

    data class UpdateUserIndustryParams(
        val industryId: Int
    )
}