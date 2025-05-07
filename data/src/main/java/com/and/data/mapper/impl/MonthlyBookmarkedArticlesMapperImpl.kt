package com.and.data.mapper.impl

import com.and.data.mapper.MonthlyBookmarkedArticlesMapper
import com.and.data.model.response.GetBookmarkedArticlesResponseDto.BookmarkedArticleDto
import com.and.data.model.response.GetBookmarkedArticlesResponseDto.MonthlyBookmarkedArticlesDto
import com.and.domain.model.BookmarkedArticle
import com.and.domain.model.MonthlyBookmarkedArticles
import javax.inject.Inject

class MonthlyBookmarkedArticlesMapperImpl @Inject constructor(

): MonthlyBookmarkedArticlesMapper {
    override fun mapToData(input: MonthlyBookmarkedArticles): MonthlyBookmarkedArticlesDto {
        return MonthlyBookmarkedArticlesDto(
            id = input.id,
            month = input.month,
            articles = input.articles.map { article ->
                BookmarkedArticleDto(
                    brandName = article.brandName,
                    brandId = article.brandId,
                    articleTitle = article.articleTitle,
                    articleId = article.articleId,
                    sampleText = article.sampleText,
                    date = article.date,
                    imageURL = article.imageURL
                )
            }
        )
    }

    override fun mapToDomain(input: MonthlyBookmarkedArticlesDto): MonthlyBookmarkedArticles {
        return MonthlyBookmarkedArticles(
            id = input.id,
            month = input.month,
            articles = input.articles.map { article ->
                BookmarkedArticle(
                    brandName = article.brandName,
                    brandId = article.brandId,
                    articleTitle = article.articleTitle,
                    articleId = article.articleId,
                    sampleText = article.sampleText,
                    date = article.date,
                    imageURL = article.imageURL
                )
            }
        )
    }
}