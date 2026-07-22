package com.and.domain.usecase.auth

import com.and.domain.model.User
import com.and.domain.model.type.Gender
import com.and.domain.repository.UserRepository
import com.and.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class KakaoSignupUseCase @Inject constructor(
    private val repository: UserRepository
) : BaseSuspendUseCase<KakaoSignupParams, User> {

    override suspend fun invoke(parameter: KakaoSignupParams): User {
        return repository.kakaoSignup(
            signupToken = parameter.signupToken,
            nickname = parameter.nickname,
            birthYear = parameter.birthYear,
            gender = parameter.gender,
            agreements = parameter.agreements
        )
    }
}

data class KakaoSignupParams(
    val signupToken: String,
    val nickname: String,
    val birthYear: String,
    val gender: Gender,
    val agreements: List<Agreement>
)

data class Agreement(
    val type: AgreementType,
    val agreed: Boolean
)

enum class AgreementType(val value: String) {
    AGE_CONFIRMATION_OVER_14("AGE_CONFIRMATION_OVER_14"),
    TERMS_OF_SERVICE("TERMS_OF_SERVICE"),
    PERSONAL_INFORMATION_COLLECTION_AND_USE("PERSONAL_INFORMATION_COLLECTION_AND_USE"),
    MARKETING_INFORMATION_RECEIPT("MARKETING_INFORMATION_RECEIPT")
}
