package com.and.domain.usecase.auth

import com.and.domain.repository.AuthRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.auth.RequestSMSAuthUseCase.RequestSMSAuthParams

class RequestSMSAuthUseCase(
    private val repository: AuthRepository
): BaseSuspendUseCase<RequestSMSAuthParams, Unit> {

    override suspend fun invoke(parameter: RequestSMSAuthParams) {
        return repository.requestSMSAuth(
            parameter.phoneNumber
        )
    }

    data class RequestSMSAuthParams(
        val phoneNumber: String
    )
}