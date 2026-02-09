package com.and.data.mapper.impl

import com.and.data.mapper.DailyArticleStatusMapper
import com.and.data.model.response.GetMonthlyArticleStatusResponseDto.DailyArticleStatusDto
import com.and.domain.model.DailyArticleStatus
import javax.inject.Inject

class DailyArticleStatusMapperImpl @Inject constructor(
): DailyArticleStatusMapper {
    override fun mapToData(input: DailyArticleStatus): DailyArticleStatusDto {
        return DailyArticleStatusDto(
            publishDate = input.publishDate,
            hasArticles = input.hasArticles,
            totalCount = input.totalCount,
            unreadCount = input.unreadCount
        )
    }

    override fun mapToDomain(input: DailyArticleStatusDto): DailyArticleStatus {
        return DailyArticleStatus(
            publishDate = input.publishDate,
            hasArticles = input.hasArticles,
            totalCount = input.totalCount,
            unreadCount = input.unreadCount
        )
    }
}