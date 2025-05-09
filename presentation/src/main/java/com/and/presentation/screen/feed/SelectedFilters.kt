package com.and.presentation.screen.feed

import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.NewsLetterFilterCategory
import com.and.domain.model.type.SortCategory
import java.time.Instant
import java.time.ZonedDateTime

data class SelectedFilters(
    val sort: SortCategory = SortCategory.POPULARITY,
    val industries: IndustryCategory = IndustryCategory.DEFAULT,
    val date: ZonedDateTime = ZonedDateTime.now()
) {
    companion object {
        fun getIsChanged(selectedFilters: SelectedFilters, category: NewsLetterFilterCategory): Boolean {
            return when (category) {
                NewsLetterFilterCategory.SORT -> selectedFilters.sort != SortCategory.POPULARITY
                NewsLetterFilterCategory.INDUSTRY -> selectedFilters.industries != IndustryCategory.DEFAULT
                NewsLetterFilterCategory.WHEN -> selectedFilters.date.dayOfWeek != ZonedDateTime.now().dayOfWeek
            }
        }
    }
}
