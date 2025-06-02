package com.and.presentation.screen.register

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.usecase.auth.RequestSMSAuthUseCase
import com.and.domain.usecase.auth.RequestSMSAuthUseCase.RequestSMSAuthParams
import com.and.domain.usecase.user.CheckUserIdDuplicationUseCase
import com.and.domain.usecase.user.CheckUserIdDuplicationUseCase.GetUserIdDuplicationParams
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val getSMSAuthUseCase: RequestSMSAuthUseCase,
    private val getCheckUserIdDuplicationUseCase: CheckUserIdDuplicationUseCase
): ViewModel() {

    private val _authCodeRequestedState = mutableStateOf<UiState<Boolean>>(UiState.Idle)
    val authCodeRequestedState: State<UiState<Boolean>> = _authCodeRequestedState

    private val _idDuplicationState = mutableStateOf<UiState<Boolean>>(UiState.Idle)
    val idDuplicationState: State<UiState<Boolean>> = _idDuplicationState

    fun requestSmsAuthCode(
        phoneNumber: String
    ) {
        viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    getSMSAuthUseCase(
                        RequestSMSAuthParams(
                            phoneNumber = phoneNumber
                        )
                    )
                }
            }.onSuccess { result ->
                _authCodeRequestedState.value = UiState.Success(true)
            }.onFailure { error ->
                _authCodeRequestedState.value = UiState.Error(
                    error.message ?: "인증 요청 중 오류가 발생했습니다"
                )
            }
        }
    }

    fun expireAuthCodeTimer() {
        _authCodeRequestedState.value = UiState.Idle
    }

    fun checkUserIdDuplication(
        loginId: String
    ) {
        viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    getCheckUserIdDuplicationUseCase(
                        GetUserIdDuplicationParams(
                            loginId = loginId
                        )
                    )
                }
            }.onSuccess { result ->
                _idDuplicationState.value = UiState.Error("이미 존재하는 아이디")
            }.onFailure { error ->
                Log.e("RegisterViewModel", error.message ?: "오류")
                _idDuplicationState.value = UiState.Success(true)
            }
        }
    }

    fun resetIdDuplication() {
        _idDuplicationState.value = UiState.Idle
    }
}