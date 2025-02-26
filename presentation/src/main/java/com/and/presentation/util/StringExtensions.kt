package com.and.presentation.util

/**
 * 주어진 문자열의 끝 3글자를 마스킹 처리합니다.
 *
 * 예시:
 * ```
 * "lion9638" -> "lion9***"
 * ```
 *
 * @author 로키
 * @throws IllegalArgumentException 문자열 길이가 3 이하일 경우 예외가 발생합니다.
 * @receiver [com.and.presentation.model.RegisteredUser]의 id 값
 * @return 끝 3글자가 마스킹 처리된 문자열
 */
fun String.toMaskedString(): String {
    return if (this.length <= 3) {
        throw IllegalArgumentException("ID의 길이가 3보다 짧습니다")
    } else {
        this.substring(0, this.length - 3) + "***"
    }
}

/**
 * 사용자의 비밀번호가
 * (1) 영문/숫자를 모두 포함하고
 * (2) 다른 문자는 포함하지 않는지 체크합니다.
 *
 * 예시:
 * ```
 * "abcdabcd" -> false
 * "abcd1234" -> true
 * ```
 *
 * @author 로키
 * @receiver [String]
 * @return 조건 만족 여부
 */
fun String.passwordValidation(): Boolean {
    val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z0-9]{6,12}$")
    return passwordRegex.matches(this)
}

/**
 * 사용자의 닉네임이
 * (1) 특수문자를 포함하지 않음
 * 위 조건을 만족하는지 체크합니다.
 *
 * 예시:
 * ```
 * "로키*23$" -> false
 * ```
 *
 * @author 로키
 * @receiver [String]
 * @return 조건 만족 여부
 */
fun String.nicknameValidation(): Boolean {
    return this.all { it.isLetterOrDigit() }
}