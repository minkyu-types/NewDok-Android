package com.and.domain.usecase.user

import com.and.domain.model.User
import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.BaseUseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UserRepository
): BaseSuspendUseCase<LoginParams, User> {

    override suspend fun invoke(parameter: LoginParams): User {
        return repository.login(parameter.loginId, parameter.password)
    }
}

data class LoginParams(
    val loginId: String,
    val password: String
)