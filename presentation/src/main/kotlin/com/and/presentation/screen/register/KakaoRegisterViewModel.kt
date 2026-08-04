package com.and.presentation.screen.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.type.Gender
import com.and.domain.usecase.auth.Agreement
import com.and.domain.usecase.auth.AgreementType
import com.and.domain.usecase.auth.KakaoSignupParams
import com.and.domain.usecase.auth.KakaoSignupUseCase
import com.and.presentation.mapper.UserMapper
import com.and.presentation.model.UserModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KakaoRegisterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val kakaoSignupUseCase: KakaoSignupUseCase,
    private val userMapper: UserMapper,
) : ViewModel() {

    private val signupToken: String = savedStateHandle.get<String>("signupToken") ?: ""

    /** 카카오 프로필에서 가져온 닉네임 (입력 필드 초기값) */
    val initialNickname: String = savedStateHandle.get<String>("nickname") ?: ""

    private var nickname: String = ""
    private var birthYear: String = ""
    private var gender: Gender? = null

    private val _signUpState = mutableStateOf<UiState<UserModel>>(UiState.Idle)
    val signUpState: State<UiState<UserModel>> = _signUpState

    fun setUserNicknameBirthGender(
        nickname: String,
        birth: String?,
        gender: Gender?
    ) {
        this.nickname = nickname
        this.birthYear = requireNotNull(birth)
        this.gender = requireNotNull(gender)
    }

    fun signUp(marketingAgreed: Boolean) {
        if (_signUpState.value is UiState.Loading) return

        viewModelScope.launch {
            _signUpState.value = UiState.Loading
            runCatching {
                kakaoSignupUseCase(
                    KakaoSignupParams(
                        signupToken = signupToken,
                        nickname = nickname,
                        birthYear = birthYear,
                        gender = requireNotNull(gender),
                        agreements = listOf(
                            Agreement(AgreementType.AGE_CONFIRMATION_OVER_14, true),
                            Agreement(AgreementType.TERMS_OF_SERVICE, true),
                            Agreement(AgreementType.PERSONAL_INFORMATION_COLLECTION_AND_USE, true),
                            Agreement(AgreementType.MARKETING_INFORMATION_RECEIPT, marketingAgreed),
                        )
                    )
                )
            }.onSuccess { user ->
                _signUpState.value = UiState.Success(userMapper.mapToPresentation(user))
            }.onFailure { error ->
                error.printStackTrace()
                _signUpState.value = UiState.Error(
                    error.message ?: "회원가입 중 오류가 발생했습니다"
                )
            }
        }
    }

    fun consumeSignUpError() {
        if (_signUpState.value is UiState.Error) {
            _signUpState.value = UiState.Idle
        }
    }
}
