package com.and.presentation.mapper

import com.and.domain.model.User
import com.and.presentation.model.UserModel
import javax.inject.Inject

class UserMapperImpl @Inject constructor(

): UserMapper {
    override fun mapToPresentation(input: User): UserModel {
        return UserModel(
            id = input.id,
            loginId = input.loginId,
            password = input.password,
            phoneNumber = input.phoneNumber,
            nickname = input.nickname,
            birthYear = input.birthYear,
            gender = input.gender,
            emailIndex = input.emailIndex,
            subscribeEmail = input.subscribeEmail,
            subscribePassword = input.subscribePassword,
            createdAt = input.createdAt,
            industryId = input.industryId,
            interests = input.interests
        )
    }

    override fun mapToDomain(input: UserModel): User {
        return User(
            id = input.id,
            loginId = input.loginId,
            password = input.password,
            phoneNumber = input.phoneNumber,
            nickname = input.nickname,
            birthYear = input.birthYear,
            gender = input.gender,
            emailIndex = input.emailIndex,
            subscribeEmail = input.subscribeEmail,
            subscribePassword = input.subscribePassword,
            createdAt = input.createdAt,
            industryId = input.industryId,
            interests = input.interests
        )
    }
}