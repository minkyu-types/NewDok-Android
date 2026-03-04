package com.and.presentation.screen.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.usecase.user.LoginParams
import com.and.domain.usecase.user.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val _loginSuccess: MutableState<Boolean?> = mutableStateOf(null)
    val loginSuccess: State<Boolean?> = _loginSuccess

    fun login(
        id: String,
        password: String
    ) {
        viewModelScope.launch {
            runCatching {
                loginUseCase(
                    LoginParams(
                        loginId = id,
                        password = password
                    )
                )
            }.onSuccess { result ->
                _loginSuccess.value = true
            }.onFailure {
                _loginSuccess.value = false
            }
        }
    }
}