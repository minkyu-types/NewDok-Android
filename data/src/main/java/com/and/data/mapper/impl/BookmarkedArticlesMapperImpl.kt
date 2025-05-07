package com.and.data.mapper.impl

import com.and.data.mapper.BookmarkedArticlesMapper
import com.and.data.mapper.MonthlyBookmarkedArticlesMapper
import com.and.data.model.response.GetBookmarkedArticlesResponseDto.BookmarkedArticleData
import com.and.domain.model.BookmarkedArticles
import javax.inject.Inject

class BookmarkedArticlesMapperImpl @Inject constructor(
    private val monthlyBookmarkedArticlesMapper: MonthlyBookmarkedArticlesMapper,
): BookmarkedArticlesMapper {
    override fun mapToData(input: BookmarkedArticles): BookmarkedArticleData {
        return BookmarkedArticleData(
            totalAmount = input.totalAmount,
            bookmarkForMonth = input.bookmarkForMonth.map { monthData ->
                monthlyBookmarkedArticlesMapper.mapToData(monthData)
            }
        )
    }

    override fun mapToDomain(input: BookmarkedArticleData): BookmarkedArticles {
        return BookmarkedArticles(
            totalAmount = input.totalAmount,
            bookmarkForMonth = input.bookmarkForMonth.map { monthData ->
                monthlyBookmarkedArticlesMapper.mapToDomain(monthData)
            }
        )
    }
}