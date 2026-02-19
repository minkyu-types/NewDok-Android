package com.and.presentation.activity

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.and.presentation.screen.login.LoginScreen
import com.and.presentation.screen.onboarding.OnboardingScreen
import com.and.presentation.screen.preinvestigation.InvestigationFlowScreen
import com.and.presentation.screen.register.RegisterFlowScreen
import kotlinx.coroutines.launch

@Composable
fun MainNavGraph(
    startDestination: String,
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()

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
                    coroutineScope.launch {
                        viewModel.setGuestMode(true)
                        navController.navigate(ScreenFlow.MAIN.route) {
                            popUpTo(ScreenFlow.ON_BOARDING.route) { inclusive = true }
                        }
                    }
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
