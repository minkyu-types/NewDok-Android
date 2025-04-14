package com.and.domain.repository

import com.and.domain.model.NewsLetter
import com.and.domain.model.RecommendedNewsLetter
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.SortCategory
import java.time.Instant

interface MemberNewsLetterRepository {
    suspend fun getNewsLetters(
        orderOption: SortCategory,
        industry: IndustryCategory,
        date: Instant
    ): List<NewsLetter>
    suspend fun getNewsLetterById(
        newsLetterId: Int,
    ): NewsLetter
    suspend fun getRecommendedNewsLetters(): RecommendedNewsLetter
    suspend fun getSearchedNewsLetter(brandName: String): NewsLetter
    suspend fun getSubscribedNewsLetters(): List<NewsLetter>
    suspend fun getUnSubscribedNewsLetters(): List<NewsLetter>
    suspend fun updateSubscription(newsLetterId: Int): Boolean
}