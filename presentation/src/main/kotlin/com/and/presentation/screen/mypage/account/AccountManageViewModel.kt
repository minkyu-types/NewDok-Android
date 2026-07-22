package com.and.presentation.screen.mypage.account

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.usecase.user.DeleteUserAccessTokenUseCase
import com.and.domain.usecase.user.SetGuestModeUseCase
import com.and.presentation.util.KakaoLoginHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountManageViewModel @Inject constructor(
    private val deleteUserAccessTokenUseCase: DeleteUserAccessTokenUseCase,
    private val setGuestModeUseCase: SetGuestModeUseCase
): ViewModel() {

    private val _logoutResult = mutableStateOf<Boolean?>(null)
    val logoutResult: State<Boolean?> = _logoutResult

    fun clearUserData() {
        viewModelScope.launch {
            android.util.Log.d("AccountManageViewModel", "clearUserData started")
            runCatching {
                // 1. 카카오 SDK 로그아웃 시도 (실패해도 계속 진행)
                try {
                    android.util.Log.d("AccountManageViewModel", "Attempting Kakao logout")
                    KakaoLoginHelper.logout()
                    android.util.Log.d("AccountManageViewModel", "Kakao logout successful")
                } catch (e: Exception) {
                    // 카카오 로그인이 아닌 경우 또는 이미 로그아웃된 경우 무시
                    android.util.Log.d("AccountManageViewModel", "Kakao logout failed or not logged in: ${e.message}")
                    e.printStackTrace()
                }

                // 2. 앱 내부 토큰 삭제
                android.util.Log.d("AccountManageViewModel", "Deleting access token")
                val tokenDeleted = deleteUserAccessTokenUseCase(Unit)
                android.util.Log.d("AccountManageViewModel", "Token deleted: $tokenDeleted")

                // 3. 게스트 모드 초기화
                android.util.Log.d("AccountManageViewModel", "Resetting guest mode")
                setGuestModeUseCase(false)
                android.util.Log.d("AccountManageViewModel", "Guest mode reset")

                tokenDeleted
            }.onSuccess { result ->
                android.util.Log.d("AccountManageViewModel", "Logout successful: $result")
                _logoutResult.value = result
            }.onFailure { error ->
                android.util.Log.e("AccountManageViewModel", "Logout failed", error)
                error.printStackTrace()
                _logoutResult.value = false
            }
        }
    }

    fun consumeLogoutResult() {
        _logoutResult.value = null
    }
}