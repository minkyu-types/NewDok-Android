package com.and.domain.model

import com.and.domain.model.type.Gender
import com.and.domain.model.type.InterestCategory
import java.time.Instant

data class User(
    val id: Int,
    val loginId: String,
    val password: String,
    val phoneNumber: String,
    val nickname: String,
    val birthYear: String,
    val gender: Gender,
    val emailIndex: String,
    val subscribeEmail: String,
    val subscribePassword: String,
    val createdAt: Instant,
    val industryId: Int?,
    val interests: List<InterestCategory> = emptyList()
)
