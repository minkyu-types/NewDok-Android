package com.and.presentation.activity

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.and.presentation.screen.login.LoginScreen
import com.and.presentation.screen.onboarding.OnboardingScreen
import com.and.presentation.screen.preinvestigation.InvestigationFlowScreen
import com.and.presentation.screen.register.RegisterFlowScreen

@Composable
fun MainNavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(ScreenFlow.ON_BOARDING.route) {
            OnboardingScreen(
                onRegisterClick = {
                    navController.navigate(ScreenFlow.REGISTER.route)
                },
                onLoginClick = {
                    navController.navigate(ScreenFlow.LOGIN.route)
                },
                onAutoLogin = {
                    navController.navigate(ScreenFlow.MAIN.route)
                }
            )
        }

        composable(ScreenFlow.LOGIN.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(ScreenFlow.MAIN.route)
                },
                onLoginWithoutSignUp = {
                    // 비회원으로 이용
                },
                onRegister = {
                    navController.navigate(ScreenFlow.REGISTER.route)
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
                onLogout = {
                    navController.navigate(ScreenFlow.ON_BOARDING.route)
                }
            )
        }
    }
}