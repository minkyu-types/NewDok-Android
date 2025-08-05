package com.and.data.model.data

import java.time.Instant

data class UserInfoDto(
    val id: Int,
    val loginId: String,
    val password: String,
    val phoneNumber: String,
    val nickname: String,
    val birthYear: String,
    val gender: String,
    val emailIndex: String,
    val subscribeEmail: String,
    val subscribePassword: String,
    val createdAt: Instant,
    val industryId: Int,
    val interests: List<InterestModel>
) {
    data class InterestModel (
        val userId: Int,
        val interestId: Int,
        val createdAt: String,
    )
}
