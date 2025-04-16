package com.and.data.di

import com.and.data.api.article.GetArticlesApi
import com.and.data.api.article.GetBookmarkedArticlesApi
import com.and.data.api.article.GetBookmarkedInterestsApi
import com.and.data.api.article.GetArticleByIdApi
import com.and.data.api.article.GetSearchedArticlesApi
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
object ArticleModule {

    @Provides
    @Singleton
    fun providesPatchBookmarkArticleApi(retrofit: Retrofit) = retrofit.create(PatchBookmarkArticleApi::class.java)

    @Provides
    @Singleton
    fun providesGetTodayArticlesApi(retrofit: Retrofit) = retrofit.create(GetTodayArticlesApi::class.java)

    @Provides
    @Singleton
    fun providesGetSearchedArticlesApi(retrofit: Retrofit) = retrofit.create(GetSearchedArticlesApi::class.java)

    @Provides
    @Singleton
    fun providesGetReadArticleApi(retrofit: Retrofit) = retrofit.create(GetArticleByIdApi::class.java)

    @Provides
    @Singleton
    fun providesGetBookmarkedInterestApi(retrofit: Retrofit) = retrofit.create(GetBookmarkedInterestsApi::class.java)

    @Provides
    @Singleton
    fun providesGetBookmarkedArticlesApi(retrofit: Retrofit) = retrofit.create(GetBookmarkedArticlesApi::class.java)

    @Provides
    @Singleton
    fun providesGetArticlesApi(retrofit: Retrofit) = retrofit.create(GetArticlesApi::class.java)
}