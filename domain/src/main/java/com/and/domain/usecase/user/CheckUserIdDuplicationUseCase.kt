package com.and.domain.usecase.user

import com.and.domain.model.Account
import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.user.CheckUserIdDuplicationUseCase.GetUserIdDuplicationParams
import javax.inject.Inject

class CheckUserIdDuplicationUseCase @Inject constructor(
    private val repository: UserRepository
): BaseSuspendUseCase<GetUserIdDuplicationParams, Account> {

    override suspend fun invoke(parameter: GetUserIdDuplicationParams): Account {
        return repository.getUserIdDuplication(
            parameter.loginId
        )
    }

    data class GetUserIdDuplicationParams(
        val loginId: String
    )
}