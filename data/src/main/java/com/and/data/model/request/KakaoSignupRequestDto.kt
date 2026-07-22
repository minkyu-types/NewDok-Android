package com.and.data.model.request

data class KakaoSignupRequestDto(
    val signupToken: String,
    val nickname: String,
    val birthYear: String,
    val gender: String,
    val agreements: List<AgreementDto>
)

data class AgreementDto(
    val type: String,
    val agreed: Boolean
)
