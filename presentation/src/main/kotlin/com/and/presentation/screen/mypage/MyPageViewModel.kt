package com.and.presentation.screen.mypage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.usecase.user.GetUserInfoUseCase
import com.and.presentation.mapper.UserMapper
import com.and.presentation.model.UserModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val userMapper: UserMapper
): ViewModel() {

    private val _userInfoUiState = mutableStateOf<UiState<UserModel>>(UiState.Idle)
    val userInfoUiState: State<UiState<UserModel>> = _userInfoUiState

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getUserInfoUseCase(Unit)
            }.onSuccess { result ->
                val userModel = userMapper.mapToPresentation(result)
                _userInfoUiState.value = UiState.Success(userModel)
            }.onFailure { error ->
                error.printStackTrace()
            }
        }
    }
}