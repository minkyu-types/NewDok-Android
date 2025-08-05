package com.and.presentation.screen.onboarding

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.usecase.user.GetUserAccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getUserAccessTokenUseCase: GetUserAccessTokenUseCase
) : ViewModel() {
    private val _loginSuccess: MutableState<Boolean?> = mutableStateOf(null)
    val loginSuccess: State<Boolean?> = _loginSuccess

    init {
        viewModelScope.launch {
            getUserAccessTokenUseCase(Unit)
                .firstOrNull()
                .takeIf { !it.isNullOrBlank() }
                ?.let {
                    _loginSuccess.value = true
                }
        }
    }
}