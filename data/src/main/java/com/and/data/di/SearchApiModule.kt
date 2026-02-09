package com.and.data.di

import com.and.data.api.search.GetArticlesByKeywordApi
import com.and.data.api.search.GetNewsLetterByNameApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchApiModule {

    @Provides
    @Singleton
    fun providesPatchBookmarkArticleApi(retrofit: Retrofit): GetNewsLetterByNameApi = retrofit.create(
        GetNewsLetterByNameApi::class.java)

    @Provides
    @Singleton
    fun providesGetTodayArticlesApi(retrofit: Retrofit): GetArticlesByKeywordApi = retrofit.create(
        GetArticlesByKeywordApi::class.java)
}