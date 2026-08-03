package com.and.presentation.screen.mypage.account

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.usecase.article.GetReceivedArticlesCountUseCase
import com.and.domain.usecase.newsletter.member.GetSubscribedNewsLettersCountUseCase
import com.and.domain.usecase.user.DeleteUserAccessTokenUseCase
import com.and.domain.usecase.user.DeleteUserUseCase
import com.and.domain.usecase.user.GetUserInfoUseCase
import com.and.presentation.mapper.UserMapper
import com.and.presentation.model.UserModel
import com.and.presentation.util.KakaoLoginHelper
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WithdrawalViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val deleteUserAccessTokenUseCase: DeleteUserAccessTokenUseCase,
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
                KakaoLoginHelper.unlink()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            try {
                deleteUserUseCase(Unit)
                // 서버 탈퇴 후 로컬 토큰을 지우지 않으면 다음 실행 시 탈퇴한 계정으로 자동로그인됨
                runCatching { deleteUserAccessTokenUseCase(Unit) }
                    .onFailure { it.printStackTrace() }
                _userWithdrawalUiState.value = UiState.Success(true)
            } catch (e: Exception) {
                e.printStackTrace()
                _userWithdrawalUiState.value = UiState.Error(
                    message = e.message ?: "회원 탈퇴에 실패했습니다."
                )
            }
        }
    }

    fun consumeWithdrawalResult() {
        _userWithdrawalUiState.value = UiState.Idle
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            try {
                val userInfo = getUserInfoUseCase(Unit).run {
                    userMapper.mapToPresentation(this)
                }

                _userInfoUiState.value = UiState.Success(userInfo)
            } catch (e: Exception) {
                e.printStackTrace()
                _userInfoUiState.value = UiState.Error(
                    message = e.message ?: "사용자 정보를 불러오지 못했습니다."
                )
            }
        }
    }

    private fun getUserCountData() {
        viewModelScope.launch {
            try {
                val newsLetterCount = async { getSubscribedNewsLettersCountUseCase(Unit) }
                val articlesCount = async { getReceivedArticlesCountUseCase(Unit) }

                _userCountInfoUiState.value = UiState.Success(Pair(newsLetterCount.await(), articlesCount.await()))
            } catch (e: Exception) {
                e.printStackTrace()
                _userCountInfoUiState.value = UiState.Error(
                    message = e.message ?: "구독 정보를 불러오지 못했습니다."
                )
            }
        }
    }
}