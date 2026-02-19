package com.and.data.preference

import kotlinx.coroutines.flow.Flow

interface AuthPreferenceStore {
    suspend fun saveAccessToken(token: String)
    fun getAccessToken(): Flow<String?>
    suspend fun clearAccessToken(): Boolean

    suspend fun saveGuestMode(isGuest: Boolean)
    fun isGuestMode(): Flow<Boolean>
}