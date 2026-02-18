package com.and.data.di

import com.and.data.api.util.GetOptionsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilApiModule {

    @Provides
    @Singleton
    fun providesGetOptionsApi(retrofit: Retrofit): GetOptionsApi = retrofit.create(GetOptionsApi::class.java)
}
