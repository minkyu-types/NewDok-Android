package com.and.data.preference

import kotlinx.coroutines.flow.Flow

interface SettingPreferenceStore {

    val newArticleEnabled : Flow<Boolean>
    val newUpdateEnabled: Flow<Boolean>
    val recommendedNewsLettersEnabled: Flow<Boolean>

    suspend fun setNewArticle(enabled: Boolean)
    suspend fun setNewUpdate(enabled: Boolean)
    suspend fun setRecommendedNewsLetters(enabled: Boolean)

    suspend fun toggleNewArticle()
    suspend fun toggleNewUpdate()
    suspend fun toggleRecommendedNewsLetters()

    suspend fun clearAllSettings()
}