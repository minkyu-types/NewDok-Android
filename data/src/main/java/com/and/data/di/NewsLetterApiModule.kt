package com.and.data.di

import com.and.data.api.newsletter.GetMemberNewsLetterByIdApi
import com.and.data.api.newsletter.GetMemberNewsLettersApi
import com.and.data.api.newsletter.GetNonMemberNewsLettersApi
import com.and.data.api.newsletter.GetNonMemberNewsLetterByIdApi
import com.and.data.api.newsletter.GetRecommendedNewsLettersApi
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
object NewsLetterApiModule {

    @Provides
    @Singleton
    fun providesPatchSubscriptionResumeApi(retrofit: Retrofit): PatchSubscriptionResumeApi =
        retrofit.create(PatchSubscriptionResumeApi::class.java)

    @Provides
    @Singleton
    fun providesPatchSubscriptionPauseApi(retrofit: Retrofit): PatchSubscriptionPauseApi =
        retrofit.create(PatchSubscriptionPauseApi::class.java)

    @Provides
    @Singleton
    fun providesGetUnSubscribesNewsLettersApi(retrofit: Retrofit): GetUnSubscribedNewsLettersApi =
        retrofit.create(GetUnSubscribedNewsLettersApi::class.java)

    @Provides
    @Singleton
    fun providesGetSubscribedNewsLettersApi(retrofit: Retrofit): GetSubscribedNewsLettersApi =
        retrofit.create(GetSubscribedNewsLettersApi::class.java)

    @Provides
    @Singleton
    fun providesGetRecommendedNewsLettersApi(retrofit: Retrofit): GetRecommendedNewsLettersApi =
        retrofit.create(GetRecommendedNewsLettersApi::class.java)

    @Provides
    @Singleton
    fun providesGetMemberXNewsLettersByIdApi(retrofit: Retrofit): GetNonMemberNewsLetterByIdApi =
        retrofit.create(GetNonMemberNewsLetterByIdApi::class.java)

    @Provides
    @Singleton
    fun providesGetMemberXNewsLettersApi(retrofit: Retrofit): GetNonMemberNewsLettersApi =
        retrofit.create(GetNonMemberNewsLettersApi::class.java)

    @Provides
    @Singleton
    fun providesGetMemberNewsLettersApi(retrofit: Retrofit): GetMemberNewsLettersApi =
        retrofit.create(GetMemberNewsLettersApi::class.java)

    @Provides
    @Singleton
    fun providesGetMemberNewsLettersByIdApi(retrofit: Retrofit): GetMemberNewsLetterByIdApi =
        retrofit.create(GetMemberNewsLetterByIdApi::class.java)
}