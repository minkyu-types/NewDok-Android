package com.and.presentation.mapper

import com.and.domain.model.BriefNewsLetter
import com.and.domain.model.NewsLetter
import com.and.presentation.model.BriefNewsLetterModel
import com.and.presentation.model.NewsLetterModel
import javax.inject.Inject

class BriefNewsLetterMapperImpl @Inject constructor(): BriefNewsLetterMapper {
    override fun mapToPresentation(input: BriefNewsLetter): BriefNewsLetterModel {
        return BriefNewsLetterModel(
            id = input.id,
            brandName = input.brandName,
            imageUrl = input.imageUrl,
            publicationCycle = input.publicationCycle
        )
    }

    override fun mapToDomain(input: BriefNewsLetterModel): BriefNewsLetter {
        return BriefNewsLetter(
            id = input.id,
            brandName = input.brandName,
            imageUrl = input.imageUrl,
            publicationCycle = input.publicationCycle
        )
    }
}