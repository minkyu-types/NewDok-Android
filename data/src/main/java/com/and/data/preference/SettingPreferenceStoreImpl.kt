package com.and.data.preference

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.and.data.di.notificationSettingDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class SettingPreferenceStoreImpl @Inject constructor(
    @ApplicationContext private val context: Context
): SettingPreferenceStore {

    companion object {
        private val SETTING_NEW_ARTICLE = booleanPreferencesKey("setting_new_article")
        private val SETTING_NEW_UPDATE = booleanPreferencesKey("setting_new_update")
        private val SETTING_NEW_RECOMMENDED_NEWSLETTER = booleanPreferencesKey("setting_new_recommended_newsletter")

        private const val DEFAULT = false
    }

    private val dataStore = context.notificationSettingDataStore

    override val newArticleEnabled: Flow<Boolean> = dataStore.data
        .safe()
        .map { preferences ->
            preferences[SETTING_NEW_ARTICLE] ?: DEFAULT
        }

    override val newUpdateEnabled: Flow<Boolean> = dataStore.data
        .safe()
        .map { preferences ->
            preferences[SETTING_NEW_UPDATE] ?: DEFAULT
        }

    override val recommendedNewsLettersEnabled: Flow<Boolean> = dataStore.data
        .safe()
        .map { preferences ->
            preferences[SETTING_NEW_RECOMMENDED_NEWSLETTER] ?: DEFAULT
        }

    override suspend fun setNewArticle(enabled: Boolean) {
        dataStore.edit { it[SETTING_NEW_ARTICLE] = enabled }
    }

    override suspend fun setNewUpdate(enabled: Boolean) {
        dataStore.edit { it[SETTING_NEW_UPDATE] = enabled }
    }

    override suspend fun setRecommendedNewsLetters(enabled: Boolean) {
        dataStore.edit { it[SETTING_NEW_RECOMMENDED_NEWSLETTER] = enabled }
    }

    override suspend fun toggleNewArticle() {
        dataStore.edit { prefs ->
            val cur = prefs[SETTING_NEW_ARTICLE] ?: DEFAULT
            prefs[SETTING_NEW_ARTICLE] = !cur
        }
    }

    override suspend fun toggleNewUpdate() {
        dataStore.edit { prefs ->
            val cur = prefs[SETTING_NEW_UPDATE] ?: DEFAULT
            prefs[SETTING_NEW_UPDATE] = !cur
        }
    }

    override suspend fun toggleRecommendedNewsLetters() {
        dataStore.edit { prefs ->
            val cur = prefs[SETTING_NEW_RECOMMENDED_NEWSLETTER] ?: DEFAULT
            prefs[SETTING_NEW_RECOMMENDED_NEWSLETTER] = !cur
        }
    }

    override suspend fun clearAllSettings() {
        dataStore.edit { it.clear() }
    }

    private fun Flow<Preferences>.safe(): Flow<Preferences> =
        this.catch { e ->
            if (e is IOException) emit(emptyPreferences()) else throw e
        }
}