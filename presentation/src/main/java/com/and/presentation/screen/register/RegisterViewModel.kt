package com.and.presentation.screen.register

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.type.Gender
import com.and.domain.usecase.auth.RequestSMSAuthUseCase
import com.and.domain.usecase.auth.RequestSMSAuthUseCase.RequestSMSAuthParams
import com.and.domain.usecase.user.CheckUserIdDuplicationUseCase
import com.and.domain.usecase.user.CheckUserIdDuplicationUseCase.GetUserIdDuplicationParams
import com.and.domain.usecase.user.LoginParams
import com.and.domain.usecase.user.LoginUseCase
import com.and.domain.usecase.user.SignUpUseCase
import com.and.presentation.mapper.UserMapper
import com.and.presentation.model.UserModel
import com.and.presentation.model.UserRegisterModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val getSMSAuthUseCase: RequestSMSAuthUseCase,
    private val getCheckUserIdDuplicationUseCase: CheckUserIdDuplicationUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val loginUseCase: LoginUseCase,
    private val userMapper: UserMapper,
) : ViewModel() {

    private val _authCodeRequestedState =
        mutableStateOf<UiState<Pair<Boolean, String?>>>(UiState.Idle)
    val authCodeRequestedState: State<UiState<Pair<Boolean, String?>>> = _authCodeRequestedState

    private val _authVerificationState = mutableStateOf<UiState<Boolean>>(UiState.Idle)
    val authVerificationState: State<UiState<Boolean>> = _authVerificationState

    private val _idDuplicationState = mutableStateOf<UiState<Boolean>>(UiState.Idle)
    val idDuplicationState: State<UiState<Boolean>> = _idDuplicationState

    private val _isNextEnabled = mutableStateOf(false)
    val isNextEnabled: State<Boolean> = _isNextEnabled

    private var userRegisterModel = UserRegisterModel()

    private val _signUpState = mutableStateOf<UiState<UserModel>>(UiState.Idle)
    val signUpState: State<UiState<UserModel>> = _signUpState

    private var issuedAuthCode: String? = null

    fun requestSmsAuthCode(
        phoneNumber: String
    ) {
        viewModelScope.launch {
            runCatching {
                getSMSAuthUseCase(
                    RequestSMSAuthParams(
                        phoneNumber = phoneNumber
                    )
                )
            }.onSuccess { authCode ->
                issuedAuthCode = authCode
                _authVerificationState.value = UiState.Idle
                _authCodeRequestedState.value = UiState.Success(true to authCode)
            }.onFailure { error ->
                issuedAuthCode = null
                _authCodeRequestedState.value = UiState.Error(
                    error.message ?: "인증 요청 중 오류가 발생했습니다"
                )
            }
            refreshNextEnabled()
        }
    }

    fun verifyAuthCode(userInput: String): Boolean {
        if (userInput.length < 6) {
            _authVerificationState.value = UiState.Idle
            _isNextEnabled.value = false
            return false
        }

        val expected = issuedAuthCode
        val ok: Boolean =
            userInput.isNotBlank() && userInput.length == 6 && expected != null && userInput == expected
        _authVerificationState.value = if (ok) {
            _isNextEnabled.value = true
            UiState.Success(true)
        }
        else UiState.Error("인증번호가 일치하지 않습니다. 다시 시도해주세요.")
        refreshNextEnabled()
        return ok
    }

    fun setUserPhoneNumber(
        phoneNumber: String
    ) {
        userRegisterModel = userRegisterModel.copy(
            phoneNumber = phoneNumber
        )
    }

    fun expireAuthCodeTimer() {
        if (isAuthVerified()) return

        issuedAuthCode = null
        _authCodeRequestedState.value = UiState.Idle
        _authVerificationState.value = UiState.Error("인증 시간이 만료되었습니다. 다시 요청해주세요.")
        refreshNextEnabled()
    }

    fun checkUserIdDuplication(
        loginId: String
    ) {
        viewModelScope.launch {
            runCatching {
                getCheckUserIdDuplicationUseCase(
                    GetUserIdDuplicationParams(
                        loginId = loginId
                    )
                )
            }.onSuccess { result ->
                _idDuplicationState.value = UiState.Error("이미 존재하는 아이디")
            }.onFailure { error ->
                _idDuplicationState.value = UiState.Success(true)
            }
        }
    }

    fun resetIdDuplication() {
        _idDuplicationState.value = UiState.Idle
    }

    fun setUserId(
        loginId: String
    ) {
        userRegisterModel = userRegisterModel.copy(
            loginId = loginId
        )
    }

    fun setUserPassword(
        password: String
    ) {
        userRegisterModel = userRegisterModel.copy(
            password = password
        )
    }

    fun setUserNicknameBirthGender(
        nickname: String,
        birth: String?,
        gender: Gender?
    ) {
        userRegisterModel = userRegisterModel.copy(
            nickname = nickname,
            birthYear = requireNotNull(birth),
            gender = requireNotNull(gender),
        )
    }

    private fun isAuthVerified(): Boolean =
        (authVerificationState.value as? UiState.Success)?.data == true

    private fun refreshNextEnabled() {
        val phoneOk = userRegisterModel.phoneNumber.isNotBlank()
        _isNextEnabled.value = phoneOk && isAuthVerified()
    }

    fun signUp() {
        viewModelScope.launch {
            runCatching {
                signUpUseCase(
                    SignUpUseCase.SignUpParam(
                        loginId = userRegisterModel.loginId,
                        password = userRegisterModel.password,
                        phoneNumber = userRegisterModel.phoneNumber,
                        nickname = userRegisterModel.nickname,
                        birthYear = userRegisterModel.birthYear,
                        gender = requireNotNull(userRegisterModel.gender),
                    )
                )
            }.onSuccess { result ->
                val user = loginUseCase(
                    LoginParams(
                        userRegisterModel.loginId,
                        userRegisterModel.password
                    )
                )
                val userModel = userMapper.mapToPresentation(user)
                _signUpState.value = UiState.Success(userModel)
            }.onFailure { error ->
                error.printStackTrace()
            }
        }
    }
}