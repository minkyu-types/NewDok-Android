package com.and.domain.usecase.auth

import com.and.domain.repository.AuthRepository
import com.and.domain.usecase.BaseSuspendUseCase
import com.and.domain.usecase.BaseUseCase
import com.and.domain.usecase.auth.RequestSMSAuthUseCase.RequestSMSAuthParams
import javax.inject.Inject

class RequestSMSAuthUseCase @Inject constructor(
    private val repository: AuthRepository
): BaseSuspendUseCase<RequestSMSAuthParams, String> {

    override suspend fun invoke(parameter: RequestSMSAuthParams): String {
        return repository.requestSMSAuth(
            parameter.phoneNumber
        )
    }

    data class RequestSMSAuthParams(
        val phoneNumber: String
    )
}