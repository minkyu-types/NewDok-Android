package com.and.domain.repository

import com.and.domain.model.NewsLetter
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.SortCategory
import java.time.Instant

interface NonMemberNewsLetterRepository {
    suspend fun getNewsLetters(
        orderOption: SortCategory,
        industries: List<IndustryCategory>,
        dates: List<Instant>,
    ): List<NewsLetter>
    suspend fun getNewsLetterById(newsLetterId: Int): NewsLetter
}