package com.and.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.and.presentation.screen.preinvestigation.InvestigationFlowScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MainNavGraph()
            InvestigationFlowScreen(
                onFlowFinished = {

                }
            )
        }
    }
}