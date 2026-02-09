package com.and.presentation.mapper

import com.and.domain.model.NewsLetter
import com.and.domain.model.SimpleArticle
import com.and.presentation.model.NewsLetterDetailModel
import com.and.presentation.model.NewsLetterModel
import java.time.ZoneId
import javax.inject.Inject

class NewsLetterDetailMapperImpl @Inject constructor(): NewsLetterDetailMapper {
    override fun mapToPresentation(input: NewsLetter): NewsLetterDetailModel {
        return NewsLetterDetailModel(
            brandId = input.brandId,
            brandName = input.brandName,
            imageUrl = input.imageUrl,
            interests = input.interests,
            brandArticleList = input.articles.map { article ->
                NewsLetterDetailModel.BrandArticleModel(
                    article.id,
                    article.title,
                    article.date.atZone(ZoneId.systemDefault())
                )
            },
            detailDescription = input.detailDescription,
            publicationCycle = input.publicationCycle,
            subscribeUrl = input.subscribeUrl,
            subscribeCheck = true,
            isSubscribed = input.isSubscribed
        )
    }

    override fun mapToDomain(input: NewsLetterDetailModel): NewsLetter {
        return NewsLetter(
            brandId = input.brandId,
            brandName = input.brandName,
            imageUrl = input.imageUrl,
            interests = input.interests,
            articles = input.brandArticleList.map { article ->
                SimpleArticle(
                    article.id,
                    article.title,
                    article.date.toInstant()
                )
            },
            detailDescription = input.detailDescription,
            publicationCycle = input.publicationCycle,
            subscribeUrl = input.subscribeUrl,
            subscriptionCount= 0,
            isSubscribed = input.isSubscribed
        )
    }
}