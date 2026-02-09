package com.and.presentation.util.observer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.provider.Settings
import androidx.core.content.ContextCompat
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AirplaneModeObserver @Inject constructor(
    private val context: Context,
) {

    val airplaneModeStatus: Flow<Boolean> = callbackFlow {
        trySend(isAirplaneModeActivated())

        val receiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, intent: Intent?) {
                if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
                    trySend(isAirplaneModeActivated())
                }
            }
        }

        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        ContextCompat.registerReceiver(
            context,
            receiver,
            filter,
            ContextCompat.RECEIVER_EXPORTED
        )

        awaitClose {
            context.unregisterReceiver(receiver)
        }
    }

    private fun isAirplaneModeActivated(): Boolean {
        return try {
            Settings.Global.getInt(context.contentResolver, Settings.Global.AIRPLANE_MODE_ON) == 1
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}