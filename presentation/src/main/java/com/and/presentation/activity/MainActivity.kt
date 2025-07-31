package com.and.presentation.activity

import android.os.Bundle
import android.util.Log
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

    companion object {
        private const val AIRPLANE_MODE_ACTIVATED = "비행기 모드가 활성화되었습니다. 네트워크 연결이 중단됩니다."
        private const val NETWORK_DISCONNECTED = "인터넷 연결이 끊어졌습니다."
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        var isLoading = true
        splashScreen.setKeepOnScreenCondition { isLoading }

        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val isAutoLogin: String? = viewModel.getAccessToken()
                .firstOrNull()

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
                            .makeText(context, AIRPLANE_MODE_ACTIVATED, Toast.LENGTH_SHORT)
                            .show()
                    }

                    if (!isNetworkConnected) {
                        Toast
                            .makeText(context, NETWORK_DISCONNECTED, Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                MainNavGraph(
//                    ScreenFlow.MAIN.route
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