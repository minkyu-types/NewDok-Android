package com.and.data.di

import com.and.data.api.user.GetPreInvestigateNewsLettersApi
import com.and.data.api.user.GetUserByPhoneNumberApi
import com.and.data.api.user.GetUserIdDuplicationApi
import com.and.data.api.user.PatchUserIndustryApi
import com.and.data.api.user.PatchUserInterestsApi
import com.and.data.api.user.PatchUserNicknameApi
import com.and.data.api.user.PatchUserPasswordApi
import com.and.data.api.user.PatchUserPhoneNumberApi
import com.and.data.api.user.PostLoginApi
import com.and.data.api.user.PostSignUpApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserApiModule {

    @Provides
    @Singleton
    fun providesPostSignUpApi(retrofit: Retrofit) = retrofit.create(PostSignUpApi::class.java)

    @Provides
    @Singleton
    fun providesPostLoginApi(retrofit: Retrofit) = retrofit.create(PostLoginApi::class.java)

    @Provides
    @Singleton
    fun providesPatchUserPhoneNumberApi(retrofit: Retrofit) = retrofit.create(PatchUserPhoneNumberApi::class.java)

    @Provides
    @Singleton
    fun providesPatchUserPasswordApi(retrofit: Retrofit) = retrofit.create(PatchUserPasswordApi::class.java)

    @Provides
    @Singleton
    fun providesPatchUserNicknameApi(retrofit: Retrofit) = retrofit.create(PatchUserNicknameApi::class.java)

    @Provides
    @Singleton
    fun providesPatchUserInterestApi(retrofit: Retrofit) = retrofit.create(PatchUserInterestsApi::class.java)

    @Provides
    @Singleton
    fun providesPatchUserIndustryApi(retrofit: Retrofit) = retrofit.create(PatchUserIndustryApi::class.java)

    @Provides
    @Singleton
    fun providesGetUserIdDuplicationApi(retrofit: Retrofit) = retrofit.create(GetUserIdDuplicationApi::class.java)

    @Provides
    @Singleton
    fun providesGetUserByPhoneNumberApi(retrofit: Retrofit) = retrofit.create(GetUserByPhoneNumberApi::class.java)

    @Provides
    @Singleton
    fun providesGetPreInvestigationNewsLettersApi(retrofit: Retrofit) = retrofit.create(GetPreInvestigateNewsLettersApi::class.java)
}