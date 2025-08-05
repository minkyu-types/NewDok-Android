package com.and.data.di

import com.and.data.repository.ArticleRepositoryImpl
import com.and.data.repository.AuthRepositoryImpl
import com.and.data.repository.MemberNewsLetterRepositoryImpl
import com.and.data.repository.NonMemberNewsLetterRepositoryImpl
import com.and.data.repository.UserRepositoryImpl
import com.and.domain.repository.ArticleRepository
import com.and.domain.repository.AuthRepository
import com.and.domain.repository.MemberNewsLetterRepository
import com.and.domain.repository.NonMemberNewsLetterRepository
import com.and.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    fun bindsArticleRepository(
        articleRepositoryImpl: ArticleRepositoryImpl
    ): ArticleRepository

    @Binds
    @Singleton
    fun bindsMemberNewsLetterRepository(
        memberNewsLetterRepositoryImpl: MemberNewsLetterRepositoryImpl
    ): MemberNewsLetterRepository

    @Binds
    @Singleton
    fun bindsNonMemberNewsLetterRepository(
        nonMemberNewsLetterRepositoryImpl: NonMemberNewsLetterRepositoryImpl
    ): NonMemberNewsLetterRepository

    @Binds
    @Singleton
    fun bindsAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}