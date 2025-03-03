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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.and.presentation.R
import com.and.presentation.component.IconSnackbar
import com.and.presentation.component.ProgressTopBar
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
            startDestination = InvestigationStep.STEP_1_INDUSTRY.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(InvestigationStep.STEP_1_INDUSTRY.route) {
                InvestigationStep1Screen(
                    onNext = { navController.navigate(InvestigationStep.STEP_2_INTERESTS.route) },
                    onBack = { navController.popBackStack() }
                )
            }
            composable(InvestigationStep.STEP_2_INTERESTS.route) {
                InvestigationStep2Screen(
                    onNext = { navController.navigate(InvestigationStep.STEP_3_NEWSLETTER.route) },
                    onBack = { navController.popBackStack() }
                )
            }
            composable(InvestigationStep.STEP_3_NEWSLETTER.route) {
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
                    }
                )
            }
        }
    }
}