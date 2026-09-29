package com.and.data.mapper.impl

import com.and.data.mapper.BriefNewsLetterMapper
import com.and.data.model.response.GetSubscribedNewsLettersResponseDto
import com.and.domain.model.BriefNewsLetter
import javax.inject.Inject

class BriefNewsLetterMapperImpl @Inject constructor(

): BriefNewsLetterMapper {

    override fun mapToData(input: BriefNewsLetter): GetSubscribedNewsLettersResponseDto.BriefNewsLetterDto {
        return GetSubscribedNewsLettersResponseDto.BriefNewsLetterDto(
            id = input.id,
            brandName = input.brandName,
            imageUrl = input.imageUrl,
            publicationCycle = input.publicationCycle
        )
    }

    override fun mapToDomain(input: GetSubscribedNewsLettersResponseDto.BriefNewsLetterDto): BriefNewsLetter {
        return BriefNewsLetter(
            id = input.id,
            brandName = input.brandName,
            imageUrl = input.imageUrl,
            publicationCycle = input.publicationCycle
        )
    }
}