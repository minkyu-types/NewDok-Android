package com.and.domain.model.type

enum class PublicationDay(val value: String, val dayId: Int) {
    MONDAY("월요일", 1),
    TUESDAY("화요일", 2),
    WEDNESDAY("수요일", 3),
    THURSDAY("목요일", 4),
    FRIDAY("금요일", 5),
    SATURDAY("토요일", 6),
    SUNDAY("일요일", 7),
    GUITAR_LOL("기타", 8);

    companion object {
        fun getStringValues(): List<String> {
            return entries.map { it.value }
        }
    }
}