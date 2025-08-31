package com.and.data.di

import com.and.data.preference.UserAuthPreferenceStoreImpl
import com.and.data.preference.SettingPreferenceStore
import com.and.data.preference.SettingPreferenceStoreImpl
import com.and.data.preference.UserAuthPreferenceStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {

    @Binds
    @Singleton
    abstract fun bindsUserAuthDataStore(
        impl: UserAuthPreferenceStoreImpl
    ): UserAuthPreferenceStore

    @Binds
    @Singleton
    abstract fun bindsSettingDataStore(
        impl: SettingPreferenceStoreImpl
    ): SettingPreferenceStore
}