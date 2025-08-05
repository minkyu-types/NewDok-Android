package com.and.presentation.screen.preinvestigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.and.newdok.presentation.R
import com.and.presentation.component.IconSnackbar
import com.and.presentation.component.topbar.ProgressTopBar
import kotlinx.coroutines.launch

@Composable
fun InvestigationFlowScreen(
    onFlowFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute: String = currentBackStackEntry?.destination?.route ?: InvestigationStep.STEP_1_INDUSTRY.route
    val currentProgress: Int = InvestigationStep.getStepByRoute(currentRoute)
    val snackState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            ProgressTopBar(
                title = stringResource(id = R.string.pre_investigation_curation_header),
                currentProgress = currentProgress,
                maxProgress = 3,
                onNavigationIconClick = {
                    navController.popBackStack()
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackState) { snackbarData ->
                IconSnackbar(snackbarData.visuals.message)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "pre_investigation_graph",
            modifier = Modifier.padding(innerPadding)
        ) {
            navigation(
                route = "pre_investigation_graph",
                startDestination = InvestigationStep.STEP_1_INDUSTRY.route
            ) {
                composable(InvestigationStep.STEP_1_INDUSTRY.route) { backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry("pre_investigation_graph")
                    }
                    val viewModel: InvestigationViewModel = hiltViewModel(parentEntry)

                    InvestigationStep1Screen(
                        onNext = { navController.navigate(InvestigationStep.STEP_2_INTERESTS.route) },
                        onBack = { navController.popBackStack() },
                        viewModel = viewModel
                    )
                }
                composable(InvestigationStep.STEP_2_INTERESTS.route) { backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry("pre_investigation_graph")
                    }
                    val viewModel: InvestigationViewModel = hiltViewModel(parentEntry)

                    InvestigationStep2Screen(
                        onNext = { navController.navigate(InvestigationStep.STEP_3_NEWSLETTER.route) },
                        onBack = { navController.popBackStack() },
                        viewModel = viewModel
                    )
                }
                composable(InvestigationStep.STEP_3_NEWSLETTER.route) { backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry("pre_investigation_graph")
                    }
                    val viewModel: InvestigationViewModel = hiltViewModel(parentEntry)

                    InvestigationStep3Screen(
                        onNext = { onFlowFinished() },
                        onBack = { navController.popBackStack() },
                        onSubscribeClick = {
                            // 바텀 시트 올라온 이후에 스낵바 보여주기
                            coroutineScope.launch {
                                snackState.showSnackbar(
                                    message = "내 구독용 이메일 주소가 복사되었어요"
                                )
                            }
                        },
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}