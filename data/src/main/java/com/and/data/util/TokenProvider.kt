package com.and.data.util

import kotlinx.coroutines.flow.Flow

interface TokenProvider {
    suspend fun saveAccessToken(token: String)
    fun getAccessToken(): Flow<String?>
    suspend fun clearAccessToken()
}