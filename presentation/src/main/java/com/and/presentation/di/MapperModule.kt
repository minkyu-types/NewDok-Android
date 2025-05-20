package com.and.presentation.di

import com.and.presentation.mapper.BookmarkedArticleMapper
import com.and.presentation.mapper.BookmarkedArticleMapperImpl
import com.and.presentation.mapper.BookmarkedArticlesMapper
import com.and.presentation.mapper.BookmarkedArticlesMapperImpl
import com.and.presentation.mapper.BriefNewsLetterMapper
import com.and.presentation.mapper.BriefNewsLetterMapperImpl
import com.and.presentation.mapper.MonthlyBookmarkedArticlesMapper
import com.and.presentation.mapper.MonthlyBookmarkedArticlesMapperImpl
import com.and.presentation.mapper.NewsLetterDetailMapper
import com.and.presentation.mapper.NewsLetterDetailMapperImpl
import com.and.presentation.mapper.NewsLetterMapper
import com.and.presentation.mapper.NewsLetterMapperImpl
import com.and.presentation.mapper.RecommendedNewsLetterMapper
import com.and.presentation.mapper.RecommendedNewsLetterMapperImpl
import com.and.presentation.mapper.UserMapper
import com.and.presentation.mapper.UserMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MapperModule {

    @Binds
    @Singleton
    fun bindsRecommendedNewsLetterMapper(
        recommendedNewsLetterMapperImpl: RecommendedNewsLetterMapperImpl
    ): RecommendedNewsLetterMapper

    @Binds
    @Singleton
    fun bindsNewsLetterMapper(
        newsLetterMapperImpl: NewsLetterMapperImpl
    ): NewsLetterMapper

    @Binds
    @Singleton
    fun bindsNewsLetterDetailMapper(
        newsLetterDetailMapperImpl: NewsLetterDetailMapperImpl
    ): NewsLetterDetailMapper

    @Binds
    @Singleton
    fun bindsBriefNewsLetterMapper(
        briefNewsLetterMapperImpl: BriefNewsLetterMapperImpl
    ): BriefNewsLetterMapper

    @Binds
    @Singleton
    fun bindsBookmarkedArticleMapper(
        bookmarkedArticleMapperImpl: BookmarkedArticleMapperImpl
    ): BookmarkedArticleMapper

    @Binds
    @Singleton
    fun bindsBookmarkedArticlesMapper(
        bookmarkedArticlesMapperImpl: BookmarkedArticlesMapperImpl
    ): BookmarkedArticlesMapper

    @Binds
    @Singleton
    fun bindsMonthlyBookmarkedArticlesMapper(
        monthlyBookmarkedArticlesMapperImpl: MonthlyBookmarkedArticlesMapperImpl
    ): MonthlyBookmarkedArticlesMapper

    @Binds
    @Singleton
    fun bindsUserMapper(
        userMapperImpl: UserMapperImpl
    ): UserMapper
}