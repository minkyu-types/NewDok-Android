package com.and.domain.repository

import com.and.domain.model.BriefNewsLetter
import com.and.domain.model.NewsLetter
import com.and.domain.model.RecommendedNewsLetter
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.RecommendedNewsLetterType
import com.and.domain.model.type.SortCategory

interface MemberNewsLetterRepository {
    suspend fun getNewsLetters(
        orderOption: SortCategory,
        industry: IndustryCategory,
        dayId: Int
    ): List<NewsLetter>
    suspend fun getNewsLetterById(
        newsLetterId: Int,
    ): NewsLetter
    suspend fun getRecommendedNewsLetters(type: RecommendedNewsLetterType): List<RecommendedNewsLetter>
    suspend fun getSubscribedNewsLetters(): List<BriefNewsLetter>
    suspend fun getUnSubscribedNewsLetters(): List<BriefNewsLetter>
    suspend fun updateSubscription(newsLetterId: Int, wasSubscribed: Boolean)
    suspend fun getSubscribedNewsLettersCount(): Int
}