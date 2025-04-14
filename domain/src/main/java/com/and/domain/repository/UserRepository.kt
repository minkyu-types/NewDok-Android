package com.and.domain.repository

import com.and.domain.model.User
import com.and.domain.model.type.Gender
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.InterestCategory
import com.and.domain.model.NewsLetter

interface UserRepository {
    suspend fun getPreInvestigateNewsLetters(industry: IndustryCategory, interests: List<InterestCategory>): List<NewsLetter>
    suspend fun getUserByPhoneNumber(phoneNumber: String): List<User>
    suspend fun getUserIdDuplication(loginId: String): User
    suspend fun updateUserIndustry(industryId: Int)
    suspend fun updateUserInterests(interests: List<InterestCategory>)
    suspend fun updateUserNickname(nickname: String): Boolean
    suspend fun updateUserPassword(loginId: String, prevPassword: String, password: String): Boolean
    suspend fun updateUserPhoneNumber(phoneNumber: String): Boolean
    suspend fun login(loginId: String, password: String): User
    suspend fun signUp(
        loginId: String,
        password: String,
        phoneNumber: String,
        nickname: String,
        birthYear: String,
        gender: Gender
    ): User
}