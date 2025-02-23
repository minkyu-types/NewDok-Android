package com.and.presentation.screen.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.and.presentation.R
import com.and.presentation.component.ProgressTopBar
import com.and.presentation.util.RegisterStep

@Composable
fun RegisterFlowScreen(
    currentStep: RegisterStep,
    onStepChange: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            ProgressTopBar(
                title = stringResource(id = R.string.register),
                currentProgress = currentStep.step,
                maxProgress = 5,
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentStep) {
                RegisterStep.STEP_1_AUTH -> RegisterStep1Screen(
                    onNext = { onStepChange(2) },
                    onBack = {
                        // 온보딩 화면 이동
                    }
                )
                RegisterStep.STEP_2_PASSWORD -> RegisterStep2Screen(
                    onNext = { onStepChange(3) },
                    onBack = { onStepChange(1) }
                )
                RegisterStep.STEP_3_USER_INFO -> RegisterStep3Screen(
                    onNext = { onStepChange(4) },
                    onBack = { onStepChange(2) }
                )
                RegisterStep.STEP_4_TERMS -> RegisterStep4Screen(
                    onNext = { onStepChange(5) },
                    onBack = { onStepChange(3) }
                )
                RegisterStep.STEP_5_COMPLETE -> RegisterStep5Screen(
                    onNext = {
                        // 홈 화면 이동
                    }
                )
            }
        }
    }
}