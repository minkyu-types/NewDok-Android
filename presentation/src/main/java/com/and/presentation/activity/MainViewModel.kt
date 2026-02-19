package com.and.presentation.activity

import androidx.lifecycle.ViewModel
import com.and.domain.usecase.user.GetUserAccessTokenUseCase
import com.and.domain.usecase.user.IsGuestModeUseCase
import com.and.domain.usecase.user.SetGuestModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserAccessTokenUseCase: GetUserAccessTokenUseCase,
    private val isGuestModeUseCase: IsGuestModeUseCase,
    private val setGuestModeUseCase: SetGuestModeUseCase
) : ViewModel() {

    fun getAccessToken() = getUserAccessTokenUseCase(Unit)
        .flowOn(Dispatchers.IO)

    fun isGuestMode() = isGuestModeUseCase(Unit)
        .flowOn(Dispatchers.IO)

    suspend fun setGuestMode(isGuest: Boolean) {
        setGuestModeUseCase(isGuest)
    }
}
