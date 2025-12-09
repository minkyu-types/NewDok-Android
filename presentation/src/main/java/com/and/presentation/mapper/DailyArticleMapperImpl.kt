package com.and.presentation.mapper

import com.and.domain.model.DailyArticleStatus
import com.and.presentation.model.DailyArticleModel

class DailyArticleMapperImpl: DailyArticleMapper {
    override fun mapToPresentation(input: DailyArticleStatus): DailyArticleModel {
        return DailyArticleModel(
            brandName = input.brandName,
            hasArticles = input.hasArticles,
            totalCount = input.totalCount,
            unreadCount = input.unreadCount
        )
    }

    override fun mapToDomain(input: DailyArticleModel): DailyArticleStatus {
        TODO("Not yet implemented")
    }
}