package com.and.domain.usecase.user

import com.and.domain.model.User
import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseUseCase
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val repository: UserRepository
): BaseUseCase<Unit, User> {

    override suspend fun invoke(parameter: Unit): User {
        return repository.getUserInfo()
    }
}