package com.and.domain.usecase.auth

import com.and.domain.model.KakaoLoginResult
import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class KakaoLoginUseCase @Inject constructor(
    private val repository: UserRepository
) : BaseSuspendUseCase<KakaoLoginParams, KakaoLoginResult> {

    override suspend fun invoke(parameter: KakaoLoginParams): KakaoLoginResult {
        return repository.kakaoLogin(parameter.code, parameter.redirectUri)
    }
}

data class KakaoLoginParams(
    val code: String,
    val redirectUri: String
)
