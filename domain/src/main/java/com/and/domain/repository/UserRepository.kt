package com.and.domain.repository

import com.and.domain.model.Account
import com.and.domain.model.KakaoLoginResult
import com.and.domain.model.User
import com.and.domain.model.type.Gender
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.InterestCategory
import com.and.domain.model.NewsLetter
import com.and.domain.usecase.auth.Agreement
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserAccessToken(): Flow<String?>
    suspend fun deleteUserAccessToken(): Boolean

    suspend fun setGuestMode(isGuest: Boolean)
    fun isGuestMode(): Flow<Boolean>

    suspend fun getPreInvestigateNewsLetters(industry: IndustryCategory, interests: List<InterestCategory>): List<NewsLetter>
    suspend fun getUserByPhoneNumber(phoneNumber: String): List<User>
    suspend fun getUserIdDuplication(loginId: String): Account
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
    ): String
    suspend fun kakaoLogin(code: String, redirectUri: String): KakaoLoginResult
    suspend fun kakaoSignup(
        signupToken: String,
        nickname: String,
        birthYear: String,
        gender: Gender,
        agreements: List<Agreement>
    ): User
    suspend fun withdrawal(): Pair<Boolean, String>
}