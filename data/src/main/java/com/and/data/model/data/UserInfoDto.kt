package com.and.data.model.data

import java.time.Instant

/**
 * GET /users/my 응답
 *
 * 소셜 로그인으로 가입한 회원은 loginId/phoneNumber 등 이메일 가입 전용 필드가 없어
 * 서버가 null을 내려주거나 필드를 생략한다. Gson은 Kotlin의 널 검사를 우회하므로
 * 해당 필드들은 반드시 nullable로 선언해야 한다.
 */
data class UserInfoDto(
    val id: Int,
    val loginId: String?,
    val password: String?,
    val phoneNumber: String?,
    val nickname: String,
    val birthYear: String,
    val gender: String,
    val emailIndex: String?,
    val subscribeEmail: String,
    val subscribePassword: String?,
    val createdAt: Instant,
    val industryId: Int?,
    val interests: List<InterestModel> = emptyList()
) {
    data class InterestModel (
        val userId: Int,
        val interestId: Int,
        val createdAt: String,
    )
}
