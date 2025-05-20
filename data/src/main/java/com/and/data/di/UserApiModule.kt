package com.and.data.di

import com.and.data.api.user.GetPreInvestigateNewsLettersApi
import com.and.data.api.user.GetUserByPhoneNumberApi
import com.and.data.api.user.GetUserIdDuplicationApi
import com.and.data.api.user.GetUserInfoApi
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
    fun providesPostSignUpApi(retrofit: Retrofit): PostSignUpApi = retrofit.create(PostSignUpApi::class.java)

    @Provides
    @Singleton
    fun providesPostLoginApi(retrofit: Retrofit): PostLoginApi = retrofit.create(PostLoginApi::class.java)

    @Provides
    @Singleton
    fun providesPatchUserPhoneNumberApi(retrofit: Retrofit): PatchUserPhoneNumberApi = retrofit.create(PatchUserPhoneNumberApi::class.java)

    @Provides
    @Singleton
    fun providesPatchUserPasswordApi(retrofit: Retrofit): PatchUserPasswordApi = retrofit.create(PatchUserPasswordApi::class.java)

    @Provides
    @Singleton
    fun providesPatchUserNicknameApi(retrofit: Retrofit): PatchUserNicknameApi = retrofit.create(PatchUserNicknameApi::class.java)

    @Provides
    @Singleton
    fun providesPatchUserInterestApi(retrofit: Retrofit): PatchUserInterestsApi = retrofit.create(PatchUserInterestsApi::class.java)

    @Provides
    @Singleton
    fun providesPatchUserIndustryApi(retrofit: Retrofit): PatchUserIndustryApi = retrofit.create(PatchUserIndustryApi::class.java)

    @Provides
    @Singleton
    fun providesGetUserIdDuplicationApi(retrofit: Retrofit): GetUserIdDuplicationApi = retrofit.create(GetUserIdDuplicationApi::class.java)

    @Provides
    @Singleton
    fun providesGetUserByPhoneNumberApi(retrofit: Retrofit): GetUserByPhoneNumberApi = retrofit.create(GetUserByPhoneNumberApi::class.java)

    @Provides
    @Singleton
    fun providesGetPreInvestigationNewsLettersApi(retrofit: Retrofit): GetPreInvestigateNewsLettersApi = retrofit.create(GetPreInvestigateNewsLettersApi::class.java)

    @Provides
    @Singleton
    fun providesGetUserInfoApi(retrofit: Retrofit): GetUserInfoApi = retrofit.create(GetUserInfoApi::class.java)
}