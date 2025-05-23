package com.and.presentation.screen.mypage.account

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.usecase.user.DeleteUserAccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountManageViewModel @Inject constructor(
    private val deleteUserAccessTokenUseCase: DeleteUserAccessTokenUseCase
): ViewModel() {

    private val _logoutResult = mutableStateOf<Boolean?>(null)
    val logoutResult: State<Boolean?> = _logoutResult

    fun clearUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                deleteUserAccessTokenUseCase(Unit)
            }.onSuccess { result ->
                _logoutResult.value = result
            }.onFailure { error ->
                error.printStackTrace()
                _logoutResult.value = false
            }
        }
    }
}