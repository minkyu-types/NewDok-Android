package com.and.presentation.mapper

import com.and.domain.model.MonthlyBookmarkedArticles
import com.and.presentation.model.bookmarkedarticle.MonthlyBookmarkedArticlesModel
import javax.inject.Inject

class MonthlyBookmarkedArticlesMapperImpl @Inject constructor(
    private val bookmarkedArticleMapper: BookmarkedArticleMapper
): MonthlyBookmarkedArticlesMapper {
    override fun mapToPresentation(input: MonthlyBookmarkedArticles): MonthlyBookmarkedArticlesModel {
        return MonthlyBookmarkedArticlesModel(
            id = input.id,
            month = input.month,
            articles = input.articles.map {
                bookmarkedArticleMapper.mapToPresentation(it)
            }
        )
    }

    override fun mapToDomain(input: MonthlyBookmarkedArticlesModel): MonthlyBookmarkedArticles {
        return MonthlyBookmarkedArticles(
            id = input.id,
            month = input.month,
            articles = input.articles.map {
                bookmarkedArticleMapper.mapToDomain(it)
            }
        )
    }
}