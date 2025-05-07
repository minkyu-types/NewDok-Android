package com.and.data.model.data

import java.time.Instant

data class UserDto(
    val id: Int,
    val loginId: String,
    val phoneNumber: String,
    val subscribeEmail: String,
    val nickname: String,
    val birthYear: String,
    val gender: String,
    val createdAt: Instant,
    val industryId: Int,
    val interests: List<UserInterestDto>
)
