package com.and.data.mapper.impl

import com.and.data.mapper.UserMapper
import com.and.data.model.data.UserDto
import com.and.data.model.data.UserInterestDto
import com.and.domain.model.User
import com.and.domain.model.type.Gender
import com.and.domain.model.type.InterestCategory
import javax.inject.Inject

class UserMapperImpl @Inject constructor(): UserMapper {
    override fun mapToData(input: User): UserDto {
        return UserDto(
            input.id,
            input.loginId,
            input.phoneNumber,
            input.subscribeEmail,
            input.nickname,
            input.birthYear,
            input.gender.value,
            input.createdAt,
            input.industryId,
            input.interests.map {
                UserInterestDto(
                    input.id,
                    it.id,
                    createdAt = input.createdAt
                )
            },
        )
    }

    override fun mapToDomain(input: UserDto): User {
        return User(
            input.id,
            input.loginId,
            input.phoneNumber,
            input.subscribeEmail,
            input.nickname,
            input.birthYear,
            Gender.getGender(input.gender),
            input.createdAt,
            input.industryId,
            input.interests.map {
                InterestCategory.getInterestById(it.interestId)
            },
            ""
        )
    }
}