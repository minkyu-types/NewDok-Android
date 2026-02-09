package com.and.presentation.screen.mypage.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.InterestCategory
import com.and.domain.usecase.user.GetUserInfoUseCase
import com.and.domain.usecase.user.UpdateUserIndustryUseCase
import com.and.domain.usecase.user.UpdateUserInterestsUseCase
import com.and.domain.usecase.user.UpdateUserNicknameUseCase
import com.and.presentation.mapper.UserMapper
import com.and.presentation.model.UserModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val updateInterestsUseCase: UpdateUserInterestsUseCase,
    private val updateIndustryUseCase: UpdateUserIndustryUseCase,
    private val updateUserNicknameUseCase: UpdateUserNicknameUseCase,
    private val userMapper: UserMapper
): ViewModel() {

    private val _userInfoUiState = mutableStateOf<UiState<UserModel>>(UiState.Idle)
    val userInfoUiState: State<UiState<UserModel>> = _userInfoUiState

    init {
        getUserInfo()
    }

    fun getUserInfo() {
        viewModelScope.launch {
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

    fun updateInterests(
        interests: Set<InterestCategory>
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                updateInterestsUseCase(
                    UpdateUserInterestsUseCase.UpdateUserInterestsParams(
                        interests
                    )
                )
            }.onSuccess { result ->
                //
            }.onFailure { e ->
                e.printStackTrace()
            }
        }
    }

    fun updateIndustry(
        industry: IndustryCategory
    ) {
        viewModelScope.launch {
            runCatching {
                updateIndustryUseCase(
                    UpdateUserIndustryUseCase.UpdateUserIndustryParams(
                        industry.id
                    )
                )
            }.onSuccess { result ->

            }.onFailure { e ->
                e.printStackTrace()
            }
        }
    }

    fun updateNickName(
        nickname: String
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                updateUserNicknameUseCase(
                    UpdateUserNicknameUseCase.UpdateUserNickNameParams(
                        nickname = nickname
                    )
                )
            }.onSuccess { result ->
                //
            }.onFailure { e ->
                e.printStackTrace()
            }
        }
    }
}