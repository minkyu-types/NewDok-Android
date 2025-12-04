package com.and.domain.model.type

enum class Gender(val value: String) {
    MALE("남자"),
    FEMALE("여자"),
    OTHERS("그외");

    companion object {
        fun getGender(str: String): Gender {
            return Gender.entries.singleOrNull { it.value == str }
                ?: throw IllegalArgumentException("Wrong gender string $str")
        }
    }
}