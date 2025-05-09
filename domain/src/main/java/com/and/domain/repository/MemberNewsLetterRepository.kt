package com.and.domain.repository

import com.and.domain.model.BriefNewsLetter
import com.and.domain.model.NewsLetter
import com.and.domain.model.RecommendedNewsLetter
import com.and.domain.model.RecommendedNewsLetters
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.SortCategory
import java.time.Instant
import java.time.ZonedDateTime

interface MemberNewsLetterRepository {
    suspend fun getNewsLetters(
        orderOption: SortCategory,
        industry: IndustryCategory,
        date: ZonedDateTime
    ): List<NewsLetter>
    suspend fun getNewsLetterById(
        newsLetterId: Int,
    ): NewsLetter
    suspend fun getRecommendedNewsLetters(): RecommendedNewsLetters
    suspend fun getSearchedNewsLetter(brandName: String): NewsLetter
    suspend fun getSubscribedNewsLetters(): List<BriefNewsLetter>
    suspend fun getUnSubscribedNewsLetters(): List<BriefNewsLetter>
    suspend fun updateSubscription(newsLetterId: Int): Boolean
}