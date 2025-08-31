package com.and.data.preference

import kotlinx.coroutines.flow.Flow

interface UserAuthPreferenceStore {
    suspend fun saveAccessToken(token: String)
    fun getAccessToken(): Flow<String?>
    suspend fun clearAccessToken(): Boolean
}