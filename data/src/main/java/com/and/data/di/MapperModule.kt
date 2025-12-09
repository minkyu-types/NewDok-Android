package com.and.data.di

import com.and.data.mapper.ArticleMapper
import com.and.data.mapper.BookmarkedArticleMapper
import com.and.data.mapper.BookmarkedArticlesMapper
import com.and.data.mapper.BriefNewsLetterMapper
import com.and.data.mapper.DailyArticleStatusMapper
import com.and.data.mapper.MonthlyBookmarkedArticlesMapper
import com.and.data.mapper.NewsLetterDetailMapper
import com.and.data.mapper.NewsLetterMapper
import com.and.data.mapper.RecommendedNewsLetterMapper
import com.and.data.mapper.UserMapper
import com.and.data.mapper.impl.ArticleMapperImpl
import com.and.data.mapper.impl.BookmarkedArticleMapperImpl
import com.and.data.mapper.impl.BookmarkedArticlesMapperImpl
import com.and.data.mapper.impl.DailyArticleStatusMapperImpl
import com.and.data.mapper.impl.MonthlyBookmarkedArticlesMapperImpl
import com.and.data.mapper.impl.NewsLetterDetailMapperImpl
import com.and.data.mapper.impl.BriefNewsLetterMapperImpl
import com.and.data.mapper.impl.NewsLetterMapperImpl
import com.and.data.mapper.impl.RecommendedNewsLetterMapperImpl
import com.and.data.mapper.impl.UserMapperImpl
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
    fun bindsDailyArticleMapper(
        dailyArticleMapperImpl: DailyArticleStatusMapperImpl
    ): DailyArticleStatusMapper

    @Binds
    @Singleton
    fun bindsArticleMapper(
        articleMapperImpl: ArticleMapperImpl
    ): ArticleMapper

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
    fun bindsBookmarkedArticleMapper(
        bookmarkedArticleMapperImpl: BookmarkedArticleMapperImpl
    ): BookmarkedArticleMapper

    @Binds
    @Singleton
    fun bindsUserMapper(
        userMapperImpl: UserMapperImpl
    ): UserMapper

    @Binds
    @Singleton
    fun bindsNewsLetterMapper(
        newsLetterMapperImpl: NewsLetterMapperImpl
    ): NewsLetterMapper

    @Binds
    @Singleton
    fun bindsBriefNewsLetterMapper(
        briefNewsLetterMapperImpl: BriefNewsLetterMapperImpl
    ): BriefNewsLetterMapper

    @Binds
    @Singleton
    fun bindsNewsLetterDetailMapper(
        newsLetterDetailMapperImpl: NewsLetterDetailMapperImpl
    ): NewsLetterDetailMapper

    @Binds
    @Singleton
    fun bindsRecommendedNewsLetterMapper(
        recommendedNewsLetterMapperImpl: RecommendedNewsLetterMapperImpl
    ): RecommendedNewsLetterMapper
}