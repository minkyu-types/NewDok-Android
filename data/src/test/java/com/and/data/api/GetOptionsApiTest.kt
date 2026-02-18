package com.and.data.api

import com.and.data.api.util.GetOptionsApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * GetOptionsApi 실제 서버 호출 테스트
 */
class GetOptionsApiTest {

    private lateinit var api: GetOptionsApi

    @Before
    fun setup() {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newdok.shop")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        api = retrofit.create(GetOptionsApi::class.java)
    }

    @Test
    fun `getOptions API 호출 테스트`() = runBlocking {
        val response = api.getOptions()

        println("=== GetOptions API Response ===")
        println("Industries (${response.industries.size}개):")
        response.industries.forEach { industry ->
            println("  - [id=${industry.id}] ${industry.name}")
        }

        println()
        println("Interests (${response.interests.size}개):")
        response.interests.forEach { interest ->
            println("  - [id=${interest.id}] ${interest.name}")
        }

        println()
        println("Days (${response.days.size}개):")
        response.days.forEach { day ->
            println("  - [id=${day.id}] ${day.name}")
        }
        println("===============================")

        // 기본 검증
        assert(response.industries.isNotEmpty()) { "Industries should not be empty" }
        assert(response.interests.isNotEmpty()) { "Interests should not be empty" }
        assert(response.days.isNotEmpty()) { "Days should not be empty" }
    }
}
