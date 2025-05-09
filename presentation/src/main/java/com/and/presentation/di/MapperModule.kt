package com.and.presentation.di

import com.and.presentation.mapper.BriefNewsLetterMapper
import com.and.presentation.mapper.BriefNewsLetterMapperImpl
import com.and.presentation.mapper.NewsLetterMapper
import com.and.presentation.mapper.NewsLetterMapperImpl
import com.and.presentation.mapper.RecommendedNewsLetterMapper
import com.and.presentation.mapper.RecommendedNewsLetterMapperImpl
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
    fun bindsBriefNewsLetterMapper(
        briefNewsLetterMapperImpl: BriefNewsLetterMapperImpl
    ): BriefNewsLetterMapper
}