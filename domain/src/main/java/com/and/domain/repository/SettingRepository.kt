package com.and.domain.repository

import kotlinx.coroutines.flow.Flow

data class SettingsState(
    val newArticleEnabled: Boolean = false,
    val newUpdateEnabled: Boolean = false,
    val recommendedNewsletterEnabled: Boolean = false
)

interface SettingRepository {
    val settings: Flow<SettingsState>

    suspend fun setNewArticle(enabled: Boolean)
    suspend fun setNewUpdate(enabled: Boolean)
    suspend fun setRecommendedNewsletter(enabled: Boolean)

    suspend fun toggleNewArticle()
    suspend fun toggleNewUpdate()
    suspend fun toggleRecommendedNewsletter()

    suspend fun clearAll()
}