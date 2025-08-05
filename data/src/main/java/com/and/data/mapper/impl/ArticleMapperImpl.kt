package com.and.data.mapper.impl

import com.and.data.mapper.ArticleMapper
import com.and.data.model.data.ArticleDto
import com.and.domain.model.Article
import com.and.domain.model.type.ArticleStatus
import javax.inject.Inject

class ArticleMapperImpl @Inject constructor(): ArticleMapper {
    override fun mapToData(input: Article): ArticleDto {
        return ArticleDto(
            input.brandName,
            input.imageUrl,
            input.title,
            input.articleId,
            input.status.name,
        )
    }

    override fun mapToDomain(input: ArticleDto): Article {
        return Article(
            brandName = input.brandName,
            imageUrl = input.imageUrl,
            title = input.articleTitle,
            articleId = input.articleId,
            status = if (input.status == "READ") {
                ArticleStatus.READ
            } else {
                ArticleStatus.UNREAD
            },
        )
    }
}