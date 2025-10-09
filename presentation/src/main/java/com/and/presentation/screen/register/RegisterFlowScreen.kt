package com.and.presentation.screen.register

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.and.newdok.presentation.R
import com.and.presentation.component.topbar.ProgressTopBar

@Composable
fun RegisterFlowScreen(
    onFlowFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute: String = currentBackStackEntry?.destination?.route ?: RegisterStep.STEP_1_AUTH.route
    val currentProgress: Int = RegisterStep.getStepByRoute(currentRoute)

    Scaffold(
        topBar = {
            ProgressTopBar(
                title = stringResource(id = R.string.register),
                currentProgress = currentProgress,
                maxProgress = 6,
                onNavigationIconClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "register_graph",
            modifier = Modifier
                .padding(innerPadding)
        ) {
            navigation(
                route = "register_graph",
                startDestination = RegisterStep.STEP_1_AUTH.route
            ) {
                composable(RegisterStep.STEP_1_AUTH.route) { backstackEntry ->
                    val parentEntry = remember(backstackEntry) {
                        navController.getBackStackEntry("register_graph")
                    }
                    val viewModel: RegisterViewModel = hiltViewModel(parentEntry)

                    RegisterStep1Screen(
                        onNext = { navController.navigate(RegisterStep.STEP_2_ID.route) },
                        onBack = {
                            // 온보딩 화면 이동
                        },
                        viewModel = viewModel
                    )
                }
                composable(RegisterStep.STEP_2_ID.route) { backstackEntry ->
                    val parentEntry = remember(backstackEntry) {
                        navController.getBackStackEntry("register_graph")
                    }
                    val viewModel: RegisterViewModel = hiltViewModel(parentEntry)

                    RegisterStep2Screen(
                        onNext = { navController.navigate(RegisterStep.STEP_3_PASSWORD.route) },
                        viewModel = viewModel
                    )
                }
                composable(RegisterStep.STEP_3_PASSWORD.route) { backstackEntry ->
                    val parentEntry = remember(backstackEntry) {
                        navController.getBackStackEntry("register_graph")
                    }
                    val viewModel: RegisterViewModel = hiltViewModel(parentEntry)

                    RegisterStep3Screen(
                        onNext = { navController.navigate(RegisterStep.STEP_4_USER_INFO.route) },
                        viewModel = viewModel
                    )
                }
                composable(RegisterStep.STEP_4_USER_INFO.route) { backstackEntry ->
                    val parentEntry = remember(backstackEntry) {
                        navController.getBackStackEntry("register_graph")
                    }
                    val viewModel: RegisterViewModel = hiltViewModel(parentEntry)

                    RegisterStep4Screen(
                        onNext = { navController.navigate(RegisterStep.STEP_5_TERMS.route) },
                        viewModel = viewModel
                    )
                }
                composable(RegisterStep.STEP_5_TERMS.route) { backstackEntry ->
                    val parentEntry = remember(backstackEntry) {
                        navController.getBackStackEntry("register_graph")
                    }
                    val viewModel: RegisterViewModel = hiltViewModel(parentEntry)

                    RegisterStep5Screen(
                        onNext = { email ->
                            navController.navigate("register_step_6_complete/$email")
                        },
                        viewModel = viewModel
                    )
                }
                composable(
                    route = RegisterStep.STEP_6_COMPLETE.route,
                    arguments = listOf(
                        navArgument("email") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val email = backStackEntry.arguments?.getString("email") ?: ""

                    RegisterStep6Screen(
                        onNext = { navController.navigate(RegisterStep.STEP_7_ADDITIONAL_INFO.route) },
                        subscriptionEmail = email
                    )
                }
                composable(RegisterStep.STEP_7_ADDITIONAL_INFO.route) {
                    RegisterStep7Screen(
                        onNext = { onFlowFinished() },
                    )
                }
            }
        }
    }
}