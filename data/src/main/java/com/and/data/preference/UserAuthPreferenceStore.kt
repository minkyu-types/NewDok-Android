package com.and.data.preference

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.and.data.di.userDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserAuthPreferenceStore @Inject constructor(
    @ApplicationContext private val context: Context
): AuthPreferenceStore {

    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val GUEST_MODE_KEY = booleanPreferencesKey("guest_mode")
    }

    private val preferences = context.userDataStore

    override suspend fun saveAccessToken(token: String) {
        preferences.edit { settings ->
            settings[ACCESS_TOKEN_KEY] = token
        }
    }

    override fun getAccessToken(): Flow<String?> {
        return preferences.data
            .catch { e ->
                e.printStackTrace()
                emit(emptyPreferences())
            }.map { settings ->
                settings[ACCESS_TOKEN_KEY]
            }
    }

    override suspend fun clearAccessToken(): Boolean {
        return try {
            preferences.edit { settings ->
                settings.remove(ACCESS_TOKEN_KEY)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun saveGuestMode(isGuest: Boolean) {
        preferences.edit { settings ->
            settings[GUEST_MODE_KEY] = isGuest
        }
    }

    override fun isGuestMode(): Flow<Boolean> {
        return preferences.data
            .catch { e ->
                e.printStackTrace()
                emit(emptyPreferences())
            }.map { settings ->
                settings[GUEST_MODE_KEY] ?: false
            }
    }
}