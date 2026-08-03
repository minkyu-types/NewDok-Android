package com.and.presentation.activity

import android.widget.Toast
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.and.presentation.screen.login.KakaoLoginState
import com.and.presentation.screen.login.LoginScreen
import com.and.presentation.screen.login.SocialLoginScreen
import com.and.presentation.screen.login.SocialLoginViewModel
import com.and.presentation.screen.onboarding.OnboardingScreen
import com.and.presentation.screen.preinvestigation.InvestigationFlowScreen
import com.and.presentation.screen.register.RegisterFlowScreen
import com.and.presentation.util.KakaoLoginHelper
import kotlinx.coroutines.launch

@Composable
fun MainNavGraph(
    startDestination: String,
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val isGuest by viewModel.isGuestMode().collectAsState(initial = false)

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.statusBarsPadding()
    ) {
        composable(ScreenFlow.ON_BOARDING.route) {
            OnboardingScreen(
                onRegisterClick = {
                    navController.navigate(ScreenFlow.SOCIAL_LOGIN.route)
                },
                onLoginClick = {
                    navController.navigate(ScreenFlow.LOGIN.route)
                },
                onAutoLogin = {
                    navController.navigate(ScreenFlow.MAIN.route)
                }
            )
        }

        composable(ScreenFlow.SOCIAL_LOGIN.route) {
            val context = LocalContext.current
            val socialLoginViewModel: SocialLoginViewModel = hiltViewModel()
            val kakaoLoginState by socialLoginViewModel.kakaoLoginState

            // 카카오 로그인 상태 관찰
            LaunchedEffect(kakaoLoginState) {
                when (val state = kakaoLoginState) {
                    is KakaoLoginState.Success -> {
                        Toast.makeText(context, "${state.userName}님 환영합니다!", Toast.LENGTH_SHORT).show()
                        socialLoginViewModel.resetState()
                        navController.navigate(ScreenFlow.MAIN.route) {
                            popUpTo(ScreenFlow.ON_BOARDING.route) { inclusive = true }
                        }
                    }
                    is KakaoLoginState.NeedSignup -> {
                        // TODO: 회원가입 화면으로 이동 (signupToken과 profile 전달)
                        Toast.makeText(context, "회원가입이 필요합니다.", Toast.LENGTH_SHORT).show()
                        socialLoginViewModel.resetState()
                        // navController.navigate(ScreenFlow.KAKAO_REGISTER.route + "/${state.signupToken}")
                    }
                    is KakaoLoginState.Error -> {
                        Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                        socialLoginViewModel.resetState()
                    }
                    else -> {}
                }
            }

            SocialLoginScreen(
                onKakaoLoginClick = {
                    coroutineScope.launch {
                        try {
                            // 사용자가 로그인을 취소한 경우 null 반환 → 조용히 종료
                            val authCode = KakaoLoginHelper.login(context) ?: return@launch
                            val redirectUri = "kakao${com.and.newdok.presentation.BuildConfig.KAKAO_NATIVE_APP_KEY}://oauth"
                            socialLoginViewModel.kakaoLogin(authCode, redirectUri)
                        } catch (e: Exception) {
                            Toast.makeText(context, "카카오 로그인 실패: ${e.message}", Toast.LENGTH_SHORT).show()
                            e.printStackTrace()
                        }
                    }
                },
                onGuestModeClick = {
                    coroutineScope.launch {
                        viewModel.setGuestMode(true)
                        navController.navigate(ScreenFlow.MAIN.route) {
                            popUpTo(ScreenFlow.ON_BOARDING.route) { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(ScreenFlow.LOGIN.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(ScreenFlow.MAIN.route) {
                        popUpTo(ScreenFlow.ON_BOARDING.route) { inclusive = true }
                    }
                },
                onLoginWithoutSignUp = {
                    coroutineScope.launch {
                        viewModel.setGuestMode(true)
                        navController.navigate(ScreenFlow.MAIN.route) {
                            popUpTo(ScreenFlow.ON_BOARDING.route) { inclusive = true }
                        }
                    }
                },
                onRegister = {
                    navController.navigate(ScreenFlow.SOCIAL_LOGIN.route)
                },
                onFindIdPassword = {
                    // 아이디/비밀번호 찾기로 이동
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(ScreenFlow.REGISTER.route) {
            RegisterFlowScreen(
                onFlowFinished = {
                    navController.navigate(ScreenFlow.PRE_INVESTIGATION.route)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(ScreenFlow.PRE_INVESTIGATION.route) {
            InvestigationFlowScreen(
                onFlowFinished = {
                    navController.navigate(ScreenFlow.MAIN.route)
                }
            )
        }

        composable(ScreenFlow.MAIN.route) {
            MainFlowScreen(
                rootNavController = navController,
                isGuestMode = isGuest,
                onNavigateToLogin = {
                    coroutineScope.launch {
                        viewModel.setGuestMode(false)
                        navController.navigate(ScreenFlow.LOGIN.route)
                    }
                },
                onLogout = {
                    coroutineScope.launch {
                        viewModel.setGuestMode(false)
                        navController.navigate(ScreenFlow.ON_BOARDING.route) {
                            popUpTo(ScreenFlow.MAIN.route) { inclusive = true }
                        }
                    }
                }
            )
        }
    }
}
