package com.and.domain.usecase.user

import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val repository: UserRepository
): BaseSuspendUseCase<Unit, Pair<Boolean, String>> {
    override suspend fun invoke(parameter: Unit): Pair<Boolean, String> {
        return repository.withdrawal().also {
            repository.deleteUserAccessToken()
        }
    }
}