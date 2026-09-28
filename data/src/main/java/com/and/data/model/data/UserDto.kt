package com.and.data.model.data

import java.time.Instant

/**
 * 소셜 로그인으로 가입한 회원은 loginId/phoneNumber가 없어 서버가 null을 내려준다.
 * Gson은 Kotlin의 널 검사를 우회하므로 두 필드는 반드시 nullable로 선언해야 한다.
 */
data class UserDto(
    val id: Int,
    val loginId: String?,
    val phoneNumber: String?,
    val subscribeEmail: String,
    val nickname: String,
    val birthYear: String,
    val gender: String,
    val createdAt: Instant,
    val industryId: Int?,
    val interests: List<UserInterestDto> = emptyList()
)
