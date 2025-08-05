package com.and.data.mapper.impl

import com.and.data.mapper.ArticleMapper
import com.and.data.mapper.DailyArticleMapper
import com.and.data.model.response.GetArticlesResponseDto.DailyArticlesDto
import com.and.domain.model.DailyArticles
import javax.inject.Inject

class DailyArticleMapperImpl @Inject constructor(
    private val articleMapper: ArticleMapper
): DailyArticleMapper {
    override fun mapToData(input: DailyArticles): DailyArticlesDto {
        val articles = input.receivedArticleList.map {
            articleMapper.mapToData(it)
        }
        return DailyArticlesDto(
            id = input.id,
            publishDate = input.publishDate,
            receivedUnread = input.receivedUnread,
            receivedArticleList = articles,
        )
    }

    override fun mapToDomain(input: DailyArticlesDto): DailyArticles {
        val articles = input.receivedArticleList.map {
            articleMapper.mapToDomain(it)
        }
        return DailyArticles(
            id = input.id ?: -1,
            publishDate = input.publishDate,
            receivedUnread = input.receivedUnread,
            receivedArticleList = articles,
        )
    }
}