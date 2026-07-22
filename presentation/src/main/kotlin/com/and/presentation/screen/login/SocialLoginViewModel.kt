package com.and.presentation.screen.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.KakaoLoginResult
import com.and.domain.model.KakaoProfile
import com.and.domain.usecase.auth.KakaoLoginParams
import com.and.domain.usecase.auth.KakaoLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SocialLoginViewModel @Inject constructor(
    private val kakaoLoginUseCase: KakaoLoginUseCase
) : ViewModel() {

    private val _kakaoLoginState: MutableState<KakaoLoginState> = mutableStateOf(KakaoLoginState.Idle)
    val kakaoLoginState: State<KakaoLoginState> = _kakaoLoginState

    /**
     * 카카오 로그인 수행
     *
     * @param code 카카오 Authorization Code (또는 accessToken)
     * @param redirectUri 리다이렉트 URI
     */
    fun kakaoLogin(code: String, redirectUri: String) {
        viewModelScope.launch {
            _kakaoLoginState.value = KakaoLoginState.Loading
            runCatching {
                kakaoLoginUseCase(
                    KakaoLoginParams(
                        code = code,
                        redirectUri = redirectUri
                    )
                )
            }.onSuccess { result ->
                when (result) {
                    is KakaoLoginResult.Success -> {
                        _kakaoLoginState.value = KakaoLoginState.Success(result.user.nickname)
                    }
                    is KakaoLoginResult.NeedSignup -> {
                        _kakaoLoginState.value = KakaoLoginState.NeedSignup(
                            signupToken = result.signupToken,
                            profile = result.profile
                        )
                    }
                }
            }.onFailure { error ->
                _kakaoLoginState.value = KakaoLoginState.Error(
                    error.message ?: "카카오 로그인에 실패했습니다."
                )
            }
        }
    }

    fun resetState() {
        _kakaoLoginState.value = KakaoLoginState.Idle
    }
}

sealed class KakaoLoginState {
    object Idle : KakaoLoginState()
    object Loading : KakaoLoginState()
    data class Success(val userName: String) : KakaoLoginState()
    data class NeedSignup(val signupToken: String, val profile: KakaoProfile) : KakaoLoginState()
    data class Error(val message: String) : KakaoLoginState()
}
