package com.and.presentation.model

import com.and.domain.model.type.Gender

data class UserRegisterModel(
    val loginId: String = "",
    val password: String = "",
    val phoneNumber: String = "",
    val nickname: String = "",
    val birthYear: String = "",
    val gender: Gender? = null,
)