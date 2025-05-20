package com.and.domain.usecase.user

import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.user.UpdateUserNicknameUseCase.UpdateUserNickNameParams

class UpdateUserNicknameUseCase(
    private val repository: UserRepository
): BaseSuspendUseCase<UpdateUserNickNameParams, Boolean> {

    override suspend fun invoke(parameter: UpdateUserNickNameParams): Boolean {
        return repository.updateUserNickname(
            parameter.nickname
        )
    }

    data class UpdateUserNickNameParams(
        val nickname: String
    )
}