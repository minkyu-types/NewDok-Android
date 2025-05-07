package com.and.domain.usecase.user

import com.and.domain.model.type.InterestCategory
import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.user.UpdateUserInterestsUseCase.UpdateUserInterestsParams
import javax.inject.Inject

class UpdateUserInterestsUseCase @Inject constructor(
    private val repository: UserRepository
): BaseUseCase<UpdateUserInterestsParams, Unit> {

    override suspend fun invoke(parameter: UpdateUserInterestsParams) {
        return repository.updateUserInterests(
            parameter.interests.map { it.id }
        )
    }

    data class UpdateUserInterestsParams(
        val interests: List<InterestCategory>
    )
}