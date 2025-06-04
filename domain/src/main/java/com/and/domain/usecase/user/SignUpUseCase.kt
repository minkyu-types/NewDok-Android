package com.and.domain.usecase.user

import com.and.domain.model.type.Gender
import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.user.SignUpUseCase.SignUpParam
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: UserRepository
): BaseSuspendUseCase<SignUpParam, String> {

    override suspend fun invoke(parameter: SignUpParam): String {
        return repository.signUp(
            parameter.loginId,
            parameter.password,
            parameter.phoneNumber,
            parameter.nickname,
            parameter.birthYear,
            parameter.gender,
        )
    }

    data class SignUpParam(
        val loginId: String,
        val password: String,
        val phoneNumber: String,
        val nickname: String,
        val birthYear: String,
        val gender: Gender
    )
}