package com.and.domain.usecase.user

import com.and.domain.model.User
import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.user.GetUserByPhoneNumberUseCase.GetUserByPhoneNumberParams

class GetUserByPhoneNumberUseCase(
    private val repository: UserRepository
): BaseUseCase<GetUserByPhoneNumberParams, List<User>> {

    override suspend fun invoke(parameter: GetUserByPhoneNumberParams): List<User> {
        return repository.getUserByPhoneNumber(
            parameter.phoneNumber
        )
    }

    data class GetUserByPhoneNumberParams(
        val phoneNumber: String
    )
}