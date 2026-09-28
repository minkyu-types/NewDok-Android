package com.and.presentation.screen.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.KakaoLoginResult
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
     * @param authorizationCode 카카오 인가 코드. 서버가 카카오와 교환한다.
     */
    fun kakaoLogin(authorizationCode: String) {
        viewModelScope.launch {
            _kakaoLoginState.value = KakaoLoginState.Loading
            runCatching {
                kakaoLoginUseCase(
                    KakaoLoginParams(authorizationCode = authorizationCode)
                )
            }.onSuccess { result ->
                when (result) {
                    is KakaoLoginResult.Success -> {
                        _kakaoLoginState.value = KakaoLoginState.Success(result.user.nickname)
                    }
                    is KakaoLoginResult.NeedSignup -> {
                        _kakaoLoginState.value = KakaoLoginState.NeedSignup(
                            signupToken = result.signupToken
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
    data class NeedSignup(val signupToken: String) : KakaoLoginState()
    data class Error(val message: String) : KakaoLoginState()
}
