package com.and.data.di

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
    fun providesPostSMSAuthApi(retrofit: Retrofit) = retrofit.create(PostSMSAuthApi::class.java)
}