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
            id = input.id,
            loginId = input.loginId,
            phoneNumber = input.phoneNumber,
            input.subscribeEmail,
            input.nickname,
            input.birthYear,
            input.gender.value,
            createdAt = input.createdAt,
            input.industryId ?: -1,
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
            id = input.id,
            // 소셜 로그인 회원은 서버가 null을 내려준다. 이 모듈의 기존 관례대로 빈 문자열로 채운다.
            input.loginId ?: "",
            "",
            input.phoneNumber ?: "",
            input.nickname,
            input.birthYear,
            Gender.getGender(input.gender),
            "",
            input.subscribeEmail,
            "",
            createdAt = input.createdAt,
            input.industryId,
            input.interests.mapNotNull {
                InterestCategory.getInterestById(it.interestId)
            },
        )
    }
}