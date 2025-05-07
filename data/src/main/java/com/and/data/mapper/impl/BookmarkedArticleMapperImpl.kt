package com.and.data.mapper.impl

import com.and.data.mapper.BookmarkedArticleMapper
import com.and.data.model.response.GetBookmarkedArticlesResponseDto
import com.and.domain.model.BookmarkedArticle
import javax.inject.Inject

class BookmarkedArticleMapperImpl @Inject constructor(

): BookmarkedArticleMapper {
    override fun mapToData(input: BookmarkedArticle): GetBookmarkedArticlesResponseDto.BookmarkedArticleDto {
        return GetBookmarkedArticlesResponseDto.BookmarkedArticleDto(
            brandName = input.brandName,
            brandId = input.brandId,
            articleTitle = input.articleTitle,
            articleId = input.articleId,
            sampleText = input.sampleText,
            date = input.date,
            imageURL = input.imageURL
        )
    }

    override fun mapToDomain(input: GetBookmarkedArticlesResponseDto.BookmarkedArticleDto): BookmarkedArticle {
        return BookmarkedArticle(
            brandName = input.brandName,
            brandId = input.brandId,
            articleTitle = input.articleTitle,
            articleId = input.articleId,
            sampleText = input.sampleText,
            date = input.date,
            imageURL = input.imageURL
        )
    }
}