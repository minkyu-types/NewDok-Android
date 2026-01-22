package com.and.presentation.screen.mypage.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.repository.SettingsState
import com.and.domain.usecase.user.GetNotificationSettingsUseCase
import com.and.domain.usecase.user.UpdateNewArticleSettingUseCase
import com.and.domain.usecase.user.UpdateNewRecommendedNewsLettersUseCase
import com.and.domain.usecase.user.UpdateNewUpdateSettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationSettingViewModel @Inject constructor(
    private val getNotificationSettingsUseCase: GetNotificationSettingsUseCase,
    private val updateNewArticleSettingUseCase: UpdateNewArticleSettingUseCase,
    private val updateNewUpdateSettingUseCase: UpdateNewUpdateSettingUseCase,
    private val updateNewRecommendedNewsLettersUseCase: UpdateNewRecommendedNewsLettersUseCase
): ViewModel() {

    val notificationSettingsFlow = getNotificationSettingsUseCase(Unit)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000L), SettingsState())

    fun onToggleNewArticle() = viewModelScope.launch {
        updateNewArticleSettingUseCase(Unit)
    }

    fun onToggleNewUpdate() = viewModelScope.launch {
        updateNewUpdateSettingUseCase(Unit)
    }

    fun onToggleRecommendedNewsLetters() = viewModelScope.launch {
        updateNewRecommendedNewsLettersUseCase(Unit)
    }
}