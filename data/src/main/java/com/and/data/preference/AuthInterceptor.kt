package com.and.data.preference

import android.util.Log
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val userAuthPreferenceStore: UserAuthPreferenceStore
): Interceptor {

    companion object {
        private const val TAG = "AuthInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            userAuthPreferenceStore.getAccessToken().firstOrNull()
        } ?: ""

        if (token.isNotBlank()) {
            Log.d(TAG, "현재 전송하는 Authorization Token: $token")
        } else {
            Log.d(TAG, "Authorization Token 없음(빈 문자열)")
        }

        val request = chain.request().newBuilder()
            .apply {
                if (token.isNotBlank()) {
                    addHeader("Authorization", "Bearer $token")
                }
            }.build()

        return chain.proceed(request)
    }
}