package com.and.data.mapper.impl

import com.and.data.mapper.DailyArticleMapper
import com.and.data.model.response.GetArticlesByDateResponseDto
import com.and.domain.model.DailyArticle

class DailyArticleMapperImpl: DailyArticleMapper {
    override fun mapToData(input: GetArticlesByDateResponseDto.DailyArticleDto): DailyArticle {
        return DailyArticle(
            brandName = input.brandName,
        )
    }

    override fun mapToDomain(input: DailyArticle): GetArticlesByDateResponseDto.DailyArticleDto {
        TODO("Not yet implemented")
    }
}