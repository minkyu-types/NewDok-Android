package com.and.domain.usecase.user

import com.and.domain.model.User
import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.user.CheckUserIdDuplicationUseCase.GetUserIdDuplicationParams

class CheckUserIdDuplicationUseCase(
    private val repository: UserRepository
): BaseSuspendUseCase<GetUserIdDuplicationParams, User> {

    override suspend fun invoke(parameter: GetUserIdDuplicationParams): User {
        return repository.getUserIdDuplication(
            parameter.loginId
        )
    }

    data class GetUserIdDuplicationParams(
        val loginId: String
    )
}