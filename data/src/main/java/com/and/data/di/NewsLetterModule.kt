package com.and.data.di

import com.and.data.api.newsletter.GetMemberNewsLettersApi
import com.and.data.api.newsletter.GetMemberXNewsLettersApi
import com.and.data.api.newsletter.GetMemberXNewsLetterByIdApi
import com.and.data.api.newsletter.GetRecommendedNewsLettersApi
import com.and.data.api.newsletter.GetSearchedNewsLettersApi
import com.and.data.api.newsletter.GetSubscribedNewsLettersApi
import com.and.data.api.newsletter.GetUnSubscribedNewsLettersApi
import com.and.data.api.newsletter.PatchSubscriptionPauseApi
import com.and.data.api.newsletter.PatchSubscriptionResumeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsLetterModule {

    @Provides
    @Singleton
    fun providesPatchSubscriptionResumeApi(retrofit: Retrofit) = retrofit.create(PatchSubscriptionResumeApi::class.java)

    @Provides
    @Singleton
    fun providesPatchSubscriptionPauseApi(retrofit: Retrofit) = retrofit.create(PatchSubscriptionPauseApi::class.java)

    @Provides
    @Singleton
    fun providesGetUnSubscribesNewsLettersApi(retrofit: Retrofit) = retrofit.create(GetUnSubscribedNewsLettersApi::class.java)

    @Provides
    @Singleton
    fun providesGetSubscribedNewsLettersApi(retrofit: Retrofit) = retrofit.create(GetSubscribedNewsLettersApi::class.java)

    @Provides
    @Singleton
    fun providesGetSearchedNewsLettersApi(retrofit: Retrofit) = retrofit.create(GetSearchedNewsLettersApi::class.java)

    @Provides
    @Singleton
    fun providesGetRecommendedNewsLettersApi(retrofit: Retrofit) = retrofit.create(GetRecommendedNewsLettersApi::class.java)

    @Provides
    @Singleton
    fun providesGetMemberXNewsLettersByIdApi(retrofit: Retrofit) = retrofit.create(GetMemberXNewsLetterByIdApi::class.java)

    @Provides
    @Singleton
    fun providesGetMemberXNewsLettersApi(retrofit: Retrofit) = retrofit.create(GetMemberXNewsLettersApi::class.java)

    @Provides
    @Singleton
    fun providesGetMemberNewsLettersApi(retrofit: Retrofit) = retrofit.create(GetMemberNewsLettersApi::class.java)
}