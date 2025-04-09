package com.and.domain.repository

import com.and.domain.model.NewsLetter
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.SortCategory

interface NonMemberNewsLetterRepository {
    fun getNewsLetters(
        orderOption: SortCategory,
        industries: List<IndustryCategory>,
        dates: List<String>,
    ): List<NewsLetter>
    fun getNewsLetterById(newsLetterId: Int): NewsLetter
}