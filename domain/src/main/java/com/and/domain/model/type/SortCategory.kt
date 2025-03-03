package com.and.domain.model.type

enum class SortCategory(val value: String) {
    POPULARITY("인기순"),
    RECENCY("최신등록순");

    companion object {
        fun getStringValues(): List<String> {
            return entries.map { it.value }
        }
    }
}