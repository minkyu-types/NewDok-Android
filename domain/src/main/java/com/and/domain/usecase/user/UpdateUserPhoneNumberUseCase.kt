package com.and.domain.usecase.user

import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.user.UpdateUserPhoneNumberUseCase.UpdateUserPhoneParams

class UpdateUserPhoneNumberUseCase(
    private val repository: UserRepository
): BaseSuspendUseCase<UpdateUserPhoneParams, Boolean> {

    override suspend fun invoke(parameter: UpdateUserPhoneParams): Boolean {
        return repository.updateUserPhoneNumber(
            parameter.phoneNumber
        )
    }

    data class UpdateUserPhoneParams(
        val phoneNumber: String
    )
}