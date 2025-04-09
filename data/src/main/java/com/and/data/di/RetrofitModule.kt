package com.and.data.di

import com.and.data.BuildConfig
import com.and.data.util.InstantAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideInstantAdapter(): InstantAdapter = InstantAdapter()

    @Provides
    @Singleton
    fun provideMoshi(
        instantAdapter: InstantAdapter
    ): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(instantAdapter)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(

    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(5000L, TimeUnit.MILLISECONDS)
            .connectTimeout(5000L, TimeUnit.MILLISECONDS)

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)
        }

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(
                if (BuildConfig.DEBUG) {
                    BuildConfig.BASE_URL_DEV
                } else {
                    BuildConfig.BASE_URL_PRODUCT
                }
            )
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }
}