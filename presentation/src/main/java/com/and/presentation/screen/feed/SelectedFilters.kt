package com.and.presentation.screen.feed

import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.NewsLetterFilterCategory
import com.and.domain.model.type.PublicationDay
import com.and.domain.model.type.SortCategory
import java.time.Instant
import java.time.ZonedDateTime

data class SelectedFilters(
    val sort: SortCategory = SortCategory.POPULARITY,
    val industries: List<IndustryCategory> = listOf(IndustryCategory.DEFAULT),
    val days: List<PublicationDay> = emptyList(),
)