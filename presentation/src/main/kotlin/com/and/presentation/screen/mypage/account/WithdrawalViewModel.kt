package com.and.presentation.screen.mypage.account

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.usecase.article.GetReceivedArticlesCountUseCase
import com.and.domain.usecase.newsletter.member.GetSubscribedNewsLettersCountUseCase
import com.and.domain.usecase.user.DeleteUserUseCase
import com.and.domain.usecase.user.GetUserInfoUseCase
import com.and.presentation.mapper.UserMapper
import com.and.presentation.model.UserModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WithdrawalViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val getSubscribedNewsLettersCountUseCase: GetSubscribedNewsLettersCountUseCase,
    private val getReceivedArticlesCountUseCase: GetReceivedArticlesCountUseCase,
    private val userMapper: UserMapper
): ViewModel() {

    private val _userInfoUiState = mutableStateOf<UiState<UserModel>>(UiState.Idle)
    val userInfoUiState: State<UiState<UserModel>> = _userInfoUiState

    private val _userCountInfoUiState = mutableStateOf<UiState<Pair<Int, Int>>>(UiState.Idle)
    val userCountInfoUiState: State<UiState<Pair<Int, Int>>> = _userCountInfoUiState

    private val _userWithdrawalUiState = mutableStateOf<UiState<Boolean>>(UiState.Idle)
    val userWithdrawalUiState: State<UiState<Boolean>> = _userWithdrawalUiState

    init {
        getUserInfo()
        getUserCountData()
    }

    fun withdrawal() {
        viewModelScope.launch {
            _userWithdrawalUiState.value = UiState.Loading

            try {
                deleteUserUseCase(Unit)
                _userWithdrawalUiState.value = UiState.Success(true)
            } catch (e: Exception) {
                e.printStackTrace()
                _userWithdrawalUiState.value = UiState.Error(
                    message = e.message ?: "회원 탈퇴에 실패했습니다."
                )
            }
        }
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            val userInfo = getUserInfoUseCase(Unit).run {
                userMapper.mapToPresentation(this)
            }

            _userInfoUiState.value = UiState.Success(userInfo)
        }
    }

    private fun getUserCountData() {
        viewModelScope.launch {
            val newsLetterCount = async { getSubscribedNewsLettersCountUseCase(Unit) }
            val articlesCount = async { getReceivedArticlesCountUseCase(Unit) }

            _userCountInfoUiState.value = UiState.Success(Pair(newsLetterCount.await(), articlesCount.await()))
        }
    }
}