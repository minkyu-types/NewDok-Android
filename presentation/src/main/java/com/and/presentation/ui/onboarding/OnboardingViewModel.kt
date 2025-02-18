package com.and.presentation.ui.onboarding

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(

) : ViewModel() {
    private val _isAutoLogin = MutableStateFlow<Boolean>(false)
    val isAutoLogin get() = _isAutoLogin.asStateFlow()

    fun fetchAutoLogin() {
        val result = true

        _isAutoLogin.value = result
    }
}