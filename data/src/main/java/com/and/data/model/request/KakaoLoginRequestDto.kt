package com.and.data.model.request

/**
 * POST /auth/social-login 요청 본문
 *
 * authorizationCode는 카카오가 발급한 1회용 인가 코드이며, 서버가 이 코드를 카카오와 교환한다.
 * 따라서 클라이언트는 코드를 절대 교환하지 않는다(교환하면 서버가 다시 쓸 수 없다).
 * idToken은 코드를 교환하지 않는 이상 얻을 수 없어 현재 항상 null로 전송된다.
 */
data class KakaoLoginRequestDto(
    val provider: String,
    val platform: String,
    val idToken: String? = null,
    val authorizationCode: String
)
