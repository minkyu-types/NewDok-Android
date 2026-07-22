package com.and.data.di

import com.and.data.api.auth.PostKakaoLoginApi
import com.and.data.api.auth.PostKakaoSignupApi
import com.and.data.api.auth.PostSMSAuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun providesPostSMSAuthApi(retrofit: Retrofit): PostSMSAuthApi = retrofit.create(PostSMSAuthApi::class.java)

    @Provides
    @Singleton
    fun providesPostKakaoLoginApi(retrofit: Retrofit): PostKakaoLoginApi = retrofit.create(PostKakaoLoginApi::class.java)

    @Provides
    @Singleton
    fun providesPostKakaoSignupApi(retrofit: Retrofit): PostKakaoSignupApi = retrofit.create(PostKakaoSignupApi::class.java)
}