package com.and.presentation.mapper

import com.and.domain.model.BookmarkedArticles
import com.and.presentation.model.bookmarkedarticle.BookmarkedArticlesModel
import javax.inject.Inject

class BookmarkedArticlesMapperImpl @Inject constructor(
    private val monthlyBookmarkedArticlesMapper: MonthlyBookmarkedArticlesMapper
): BookmarkedArticlesMapper {
    override fun mapToPresentation(input: BookmarkedArticles): BookmarkedArticlesModel {
        return BookmarkedArticlesModel(
            totalAmount = input.totalAmount,
            bookmarkForMonth = input.bookmarkForMonth.map {
                monthlyBookmarkedArticlesMapper.mapToPresentation(it)
            }
        )
    }

    override fun mapToDomain(input: BookmarkedArticlesModel): BookmarkedArticles {
        return BookmarkedArticles(
            totalAmount = input.totalAmount,
            bookmarkForMonth = input.bookmarkForMonth.map {
                monthlyBookmarkedArticlesMapper.mapToDomain(it)
            }
        )
    }
}