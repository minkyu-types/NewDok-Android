package com.and.data.mapper.impl

import com.and.data.mapper.KakaoProfileMapper
import com.and.data.model.data.KakaoProfileDto
import com.and.domain.model.KakaoProfile
import javax.inject.Inject

class KakaoProfileMapperImpl @Inject constructor() : KakaoProfileMapper {
    override fun mapToData(input: KakaoProfile): KakaoProfileDto {
        return KakaoProfileDto(
            provider = input.provider,
            providerUserId = input.providerUserId,
            email = input.email,
            nickname = input.nickname
        )
    }

    override fun mapToDomain(input: KakaoProfileDto): KakaoProfile {
        return KakaoProfile(
            provider = input.provider,
            providerUserId = input.providerUserId,
            email = input.email,
            nickname = input.nickname
        )
    }
}
