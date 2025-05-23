package com.and.domain.repository

import com.and.domain.model.User
import com.and.domain.model.type.Gender
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.InterestCategory
import com.and.domain.model.NewsLetter
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserAccessToken(): Flow<String?>
    suspend fun deleteUserAccessToken(): Boolean

    suspend fun getPreInvestigateNewsLetters(industry: IndustryCategory, interests: List<InterestCategory>): List<NewsLetter>
    suspend fun getUserByPhoneNumber(phoneNumber: String): List<User>
    suspend fun getUserIdDuplication(loginId: String): User
    suspend fun getUserInfo(): User
    suspend fun updateUserIndustry(industryId: Int)
    suspend fun updateUserInterests(interestIds: List<Int>)
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