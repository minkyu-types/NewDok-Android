package com.and.presentation.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.and.newdok.presentation.R
import com.and.presentation.util.observer.AirplaneModeObserver
import com.and.presentation.util.observer.NetworkStatusObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var airplaneModeObserver: AirplaneModeObserver

    @Inject
    lateinit var networkStatusObserver: NetworkStatusObserver

    companion object

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        var isLoading = true
        splashScreen.setKeepOnScreenCondition { isLoading }

        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val isAutoLogin: String? = viewModel.getAccessToken()
                .firstOrNull()
            val isGuest: Boolean = viewModel.isGuestMode()
                .firstOrNull() ?: false

            isLoading = false

            setContent {
                val context = LocalContext.current
                val isAirplaneModeOn by airplaneModeObserver
                    .airplaneModeStatus
                    .collectAsState(initial = false)
                val isNetworkConnected by networkStatusObserver
                    .networkStatus
                    .collectAsState(initial = true)

                LaunchedEffect(isAirplaneModeOn, isNetworkConnected) {
                    if (isAirplaneModeOn) {
                        Toast
                            .makeText(context, context.getString(R.string.airplane_mode_activated), Toast.LENGTH_SHORT)
                            .show()
                    }

                    if (!isNetworkConnected) {
                        Toast
                            .makeText(context, context.getString(R.string.network_disconnected), Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                MainNavGraph(
                    startDestination = when {
                        isAutoLogin != null -> ScreenFlow.MAIN.route
                        isGuest -> ScreenFlow.MAIN.route
                        else -> ScreenFlow.ON_BOARDING.route
                    }
                )
            }
        }
    }
}