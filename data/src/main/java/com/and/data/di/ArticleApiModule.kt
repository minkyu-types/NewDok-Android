package com.and.data.di

import com.and.data.api.article.GetArticleStatusApi
import com.and.data.api.article.GetBookmarkedArticlesApi
import com.and.data.api.article.GetBookmarkedInterestsApi
import com.and.data.api.article.GetArticleByIdApi
import com.and.data.api.article.GetArticlesByDateApi
import com.and.data.api.article.GetTodayArticlesApi
import com.and.data.api.article.PatchBookmarkArticleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ArticleApiModule {

    @Provides
    @Singleton
    fun providesPatchBookmarkArticleApi(retrofit: Retrofit): PatchBookmarkArticleApi = retrofit.create(PatchBookmarkArticleApi::class.java)

    @Provides
    @Singleton
    fun providesGetTodayArticlesApi(retrofit: Retrofit): GetTodayArticlesApi = retrofit.create(GetTodayArticlesApi::class.java)

    @Provides
    @Singleton
    fun providesGetReadArticleApi(retrofit: Retrofit): GetArticleByIdApi = retrofit.create(GetArticleByIdApi::class.java)

    @Provides
    @Singleton
    fun providesGetBookmarkedInterestApi(retrofit: Retrofit): GetBookmarkedInterestsApi = retrofit.create(GetBookmarkedInterestsApi::class.java)

    @Provides
    @Singleton
    fun providesGetBookmarkedArticlesApi(retrofit: Retrofit): GetBookmarkedArticlesApi = retrofit.create(GetBookmarkedArticlesApi::class.java)

    @Provides
    @Singleton
    fun providesGetArticlesApi(retrofit: Retrofit): GetArticleStatusApi = retrofit.create(GetArticleStatusApi::class.java)

    @Provides
    @Singleton
    fun providesGetArticlesByDateApi(retrofit: Retrofit): GetArticlesByDateApi = retrofit.create(GetArticlesByDateApi::class.java)
}