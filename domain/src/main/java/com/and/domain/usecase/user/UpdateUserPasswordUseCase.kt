package com.and.domain.usecase.user

import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.user.UpdateUserPasswordUseCase.UpdateUserPasswordParams

class UpdateUserPasswordUseCase(
    private val repository: UserRepository
): BaseUseCase<UpdateUserPasswordParams, Boolean> {

    override suspend fun invoke(parameter: UpdateUserPasswordParams): Boolean {
        return repository.updateUserPassword(
            parameter.loginId,
            parameter.prevPassword,
            parameter.newPassword,
        )
    }

    data class UpdateUserPasswordParams(
        val loginId: String,
        val prevPassword: String,
        val newPassword: String
    )
}