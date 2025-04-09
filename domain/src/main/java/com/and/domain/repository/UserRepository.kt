package com.and.domain.repository

import com.and.domain.model.User
import com.and.domain.model.type.Gender
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.InterestCategory
import com.and.domain.model.NewsLetter

interface UserRepository {
    fun getPreInvestigateNewsLetters(industry: IndustryCategory, interests: List<InterestCategory>): List<NewsLetter>
    fun getUserByPhoneNumber(phoneNumber: String): List<User>
    fun getUserIdDuplication(loginId: String): User
    fun updateUserIndustry(industryId: Int)
    fun updateUserInterests(interests: List<InterestCategory>)
    fun updateUserNickname(nickname: String): Boolean
    fun updateUserPassword(loginId: String, prevPassword: String, password: String)
    fun updateUserPhoneNumber(phoneNumber: String): Boolean
    fun login(loginId: String, password: String): User
    fun signUp(
        loginId: String,
        password: String,
        phoneNumber: String,
        nickname: String,
        birthYear: String,
        gender: Gender
    ): User
}