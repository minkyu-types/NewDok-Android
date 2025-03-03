package com.and.domain.model.type

enum class PublicationDay(val value: String) {
    MONDAY("월요일"),
    TUESDAY("화요일"),
    WEDNESDAY("수요일"),
    THURSDAY("목요일"),
    FRIDAY("금요일"),
    SATURDAY("토요일"),
    SUNDAY("일요일"),
    GUITAR_LOL("기타");

    companion object {
        fun getStringValues(): List<String> {
            return entries.map { it.value }
        }
    }
}