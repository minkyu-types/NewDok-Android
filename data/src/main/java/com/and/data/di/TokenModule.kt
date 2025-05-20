package com.and.data.di

import com.and.data.preference.AuthPreferenceStore
import com.and.data.preference.UserAuthPreferenceStore
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
        impl: UserAuthPreferenceStore
    ): AuthPreferenceStore
}