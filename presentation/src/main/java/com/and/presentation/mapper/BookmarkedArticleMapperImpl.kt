package com.and.presentation.mapper

import com.and.domain.model.BookmarkedArticle
import com.and.presentation.model.bookmarkedarticle.BookmarkedArticleModel
import javax.inject.Inject

class BookmarkedArticleMapperImpl @Inject constructor(

): BookmarkedArticleMapper {
    override fun mapToPresentation(input: BookmarkedArticle): BookmarkedArticleModel {
        return BookmarkedArticleModel(
            brandName = input.brandName,
            brandId = input.brandId,
            articleTitle = input.articleTitle,
            articleId = input.articleId,
            sampleText = input.sampleText,
            date = input.date,
            thumbnailImageUrl = input.thumbnailImageUrl,
        )
    }

    override fun mapToDomain(input: BookmarkedArticleModel): BookmarkedArticle {
        return BookmarkedArticle(
            brandName = input.brandName,
            brandId = input.brandId,
            articleTitle = input.articleTitle,
            articleId = input.articleId,
            sampleText = input.sampleText,
            date = input.date,
            thumbnailImageUrl = input.thumbnailImageUrl,
        )
    }
}