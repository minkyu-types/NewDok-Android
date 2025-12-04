package com.and.data.mapper.impl

import com.and.data.mapper.DailyArticleMapper
import com.and.data.mapper.NewsLetterMapper
import com.and.data.model.response.GetArticlesResponseDto.DailyArticleDto
import com.and.domain.model.DailyArticle
import javax.inject.Inject

class DailyArticleMapperImpl @Inject constructor(
): DailyArticleMapper {
    override fun mapToData(input: DailyArticle): DailyArticleDto {
        return DailyArticleDto(
            publishDate = input.publishDate,
            hasArticles = input.hasArticles,
            totalCount = input.totalCount,
            unreadCount = input.unreadCount
        )
    }

    override fun mapToDomain(input: DailyArticleDto): DailyArticle {
        return DailyArticle(
            publishDate = input.publishDate,
            hasArticles = input.hasArticles,
            totalCount = input.totalCount,
            unreadCount = input.unreadCount
        )
    }
}