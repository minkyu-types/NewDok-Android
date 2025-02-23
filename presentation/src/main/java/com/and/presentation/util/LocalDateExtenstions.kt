package com.and.presentation.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * 주어진 [LocalDate]를 "yyyy.MM.dd 가입" 형식으로 변환합니다.
 *
 * 예시:
 * ```
 * LocalDate(2025, 2, 23) -> "2025.02.23 가입"
 * ```
 * @receiver 변환할 [LocalDate]
 * @return 형식이 변환된 "[LocalDate] + 가입"
 */
fun LocalDate.toLocalDateWithDot(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    return "${this.format(formatter)} 가입"
}