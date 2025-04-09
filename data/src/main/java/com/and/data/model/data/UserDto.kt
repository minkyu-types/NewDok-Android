package com.and.data.model.data

data class UserDto(
    val id: Int,
    val loginId: String,
    val phoneNumber: String,
    val subscribeEmail: String,
    val nickname: String,
    val birthYear: String,
    val gender: String,
    val createdAt: String,
    val industryId: Int,
    val interests: List<com.and.data.model.data.InterestDto>
)
