package com.and.domain.repository

interface AuthRepository {
    suspend fun requestSMSAuth(phoneNumber: String): String
}