package com.and.data.repository
import com.and.data.preference.SettingPreferenceStore
import com.and.data.util.IoDispatcher
import com.and.domain.repository.SettingRepository
import com.and.domain.repository.SettingsState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val settingPreferenceStore: SettingPreferenceStore
): SettingRepository, BaseRepository() {

    override val settings: Flow<SettingsState> =
        combine(
            settingPreferenceStore.newArticleEnabled,
            settingPreferenceStore.newUpdateEnabled,
            settingPreferenceStore.recommendedNewsLettersEnabled
        ) { newArticle, newUpdate, recommendedNewsletter ->
            SettingsState(
                newArticleEnabled = newArticle,
                newUpdateEnabled = newUpdate,
                recommendedNewsletterEnabled = recommendedNewsletter
            )
        }

    override suspend fun setNewArticle(enabled: Boolean) = withContext(ioDispatcher) {
        settingPreferenceStore.setNewArticle(enabled)
    }

    override suspend fun setNewUpdate(enabled: Boolean) = withContext(ioDispatcher) {
        settingPreferenceStore.setNewUpdate(enabled)
    }

    override suspend fun setRecommendedNewsletter(enabled: Boolean) = withContext(ioDispatcher) {
        settingPreferenceStore.setRecommendedNewsLetters(enabled)
    }

    override suspend fun toggleNewArticle() = settingPreferenceStore.toggleNewArticle()
    override suspend fun toggleNewUpdate() = settingPreferenceStore.toggleNewUpdate()
    override suspend fun toggleRecommendedNewsletter() = settingPreferenceStore.toggleRecommendedNewsLetters()

    override suspend fun clearAll() {
        settingPreferenceStore.clearAllSettings()
    }
}