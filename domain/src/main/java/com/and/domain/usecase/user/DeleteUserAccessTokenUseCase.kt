package com.and.domain.usecase.user

import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseFlowUseCase
import com.and.domain.usecase.BaseSuspendUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteUserAccessTokenUseCase @Inject constructor(
    private val repository: UserRepository
): BaseSuspendUseCase<Unit, Boolean> {

    override suspend fun invoke(parameter: Unit): Boolean {
        return repository.deleteUserAccessToken()
    }
}