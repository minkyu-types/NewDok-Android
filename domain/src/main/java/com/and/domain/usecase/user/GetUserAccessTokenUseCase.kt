package com.and.domain.usecase.user

import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserAccessTokenUseCase @Inject constructor(
    private val repository: UserRepository
): BaseFlowUseCase<Unit, String?> {

    override fun invoke(parameter: Unit): Flow<String?> {
        return repository.getUserAccessToken()
    }
}