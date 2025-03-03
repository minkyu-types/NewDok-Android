package com.and.domain.model.type

enum class FilterCategory(val title: String, val default: String, val items: List<String>) {
    SORT("정렬", "인기순", SortCategory.getStringValues()),
    INDUSTRY("산업 카테고리", "산업", IndustryCategory.getStringValues()),
    WHEN("발행요일", "발행요일", PublicationDay.getStringValues()),
}