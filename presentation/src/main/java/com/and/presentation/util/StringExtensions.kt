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
 * @receiver [com.and.presentation.model.RegisteredUser]의 [id]
 * @return 끝 3글자가 마스킹 처리된 문자열
 */
fun String.toMaskedString(): String {
    return if (this.length <= 3) {
        throw IllegalArgumentException("ID의 길이가 3보다 짧습니다")
    } else {
        this.substring(0, this.length - 3) + "***"
    }
}