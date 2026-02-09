package com.and.presentation.di

import android.content.Context
import com.and.presentation.util.observer.AirplaneModeObserver
import com.and.presentation.util.observer.NetworkStatusObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ObserverModule {

    @Provides
    @Singleton
    fun provideAirplaneObserver(
        @ApplicationContext appContext: Context
    ): AirplaneModeObserver = AirplaneModeObserver(appContext)

    @Provides
    @Singleton
    fun provideNetworkStatusObserver(
        @ApplicationContext appContext: Context
    ): NetworkStatusObserver = NetworkStatusObserver(appContext)
}