package com.and.data.util

import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenProvider: TokenProvider
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenProvider.getAccessToken().firstOrNull()
        } ?: ""

        val request = chain.request().newBuilder()
            .apply {
                if (token.isNotBlank()) {
                    addHeader("Authorization", "Bearer $token")
                }
            }.build()

        return chain.proceed(request)
    }
}