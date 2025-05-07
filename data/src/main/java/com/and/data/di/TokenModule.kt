package com.and.data.di

import com.and.data.util.TokenProvider
import com.and.data.util.UserTokenProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TokenModule {

    @Binds
    @Singleton
    abstract fun bindsTokenProvider(
        impl: UserTokenProvider
    ): TokenProvider
}