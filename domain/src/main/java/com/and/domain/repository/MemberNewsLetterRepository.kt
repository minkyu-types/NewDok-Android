package com.and.domain.repository

import com.and.domain.model.NewsLetter
import com.and.domain.model.RecommendedNewsLetter
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.SortCategory
import java.time.Instant

interface MemberNewsLetterRepository {
    fun getNewsLetters(
        orderOption: SortCategory,
        industry: IndustryCategory,
        date: Instant
    ): List<NewsLetter>
    fun getNewsLetterById(
        newsLetterId: Int,
    ): NewsLetter
    fun getRecommendedNewsLetters(): RecommendedNewsLetter
    fun getSearchedNewsLetter(brandName: String): NewsLetter
    fun getSubscribedNewsLetters(): List<NewsLetter>
    fun getUnSubscribedNewsLetters(): List<NewsLetter>
    fun updateSubscription(newsLetterId: Int): Boolean
}