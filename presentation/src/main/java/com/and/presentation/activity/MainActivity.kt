package com.and.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.and.newdok.presentation.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        var isLoading = true
        splashScreen.setKeepOnScreenCondition { isLoading }

        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val isAutoLogin = viewModel.getAccessToken()
                .firstOrNull()
                .takeIf { !it.isNullOrBlank() }

            isLoading = false

            setContent {
                MainNavGraph(
                    if (isAutoLogin != null) {
                        ScreenFlow.MAIN.route
                    } else {
                        ScreenFlow.ON_BOARDING.route
                    }
                )
            }
        }
    }
}