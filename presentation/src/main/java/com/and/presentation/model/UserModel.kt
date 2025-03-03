package com.and.presentation.model

import com.and.domain.model.type.Gender

data class UserModel(
    val id: Int,
    val loginId: String,
    val phoneNumber: String,
    val subscribeEmail: String,
    val nickname: String,
    val birthYear: String,
    val gender: Gender,
    val createdAt: String,
    val industryId: Int,
    val interests: List<Int>
)
