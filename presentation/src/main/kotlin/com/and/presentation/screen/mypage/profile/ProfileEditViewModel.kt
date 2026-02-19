package com.and.presentation.screen.mypage.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.Industry
import com.and.domain.model.Interest
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.InterestCategory
import com.and.domain.usecase.user.GetUserInfoUseCase
import com.and.domain.usecase.user.UpdateUserIndustryUseCase
import com.and.domain.usecase.user.UpdateUserInterestsUseCase
import com.and.domain.usecase.user.UpdateUserNicknameUseCase
import com.and.domain.usecase.util.GetIndustriesUseCase
import com.and.domain.usecase.util.GetInterestsUseCase
import com.and.presentation.mapper.UserMapper
import com.and.presentation.model.UserModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val updateInterestsUseCase: UpdateUserInterestsUseCase,
    private val updateIndustryUseCase: UpdateUserIndustryUseCase,
    private val updateUserNicknameUseCase: UpdateUserNicknameUseCase,
    private val getIndustriesUseCase: GetIndustriesUseCase,
    private val getInterestsUseCase: GetInterestsUseCase,
    private val userMapper: UserMapper
): ViewModel() {

    private val _userInfoUiState = mutableStateOf<UiState<UserModel>>(UiState.Idle)
    val userInfoUiState: State<UiState<UserModel>> = _userInfoUiState

    private val _industries = MutableStateFlow<List<Industry>>(emptyList())
    val industries: StateFlow<List<Industry>> = _industries

    private val _interestOptions = MutableStateFlow<List<Interest>>(emptyList())
    val interestOptions: StateFlow<List<Interest>> = _interestOptions

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

    fun getIndustries() {
        viewModelScope.launch {
            runCatching {
                getIndustriesUseCase(Unit)
            }.onSuccess { result ->
                _industries.value = result
            }.onFailure { error ->
                error.printStackTrace()
                _industries.value = emptyList()
            }
        }
    }

    fun getInterests() {
        viewModelScope.launch {
            runCatching {
                getInterestsUseCase(Unit)
            }.onSuccess { result ->
                _interestOptions.value = result
            }.onFailure { error ->
                error.printStackTrace()
                _interestOptions.value = emptyList()
            }
        }
    }

    fun updateInterests(
        interests: Set<Interest>
    ) {
        val categories = interests.mapNotNull { interest ->
            InterestCategory.getInterestById(interest.id)
        }.toSet()
        viewModelScope.launch {
            kotlin.runCatching {
                updateInterestsUseCase(
                    UpdateUserInterestsUseCase.UpdateUserInterestsParams(
                        categories
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
        industry: Industry
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