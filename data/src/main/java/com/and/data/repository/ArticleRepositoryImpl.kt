package com.and.data.repository

import com.and.data.api.article.GetArticleByIdApi
import com.and.data.api.article.GetArticleStatusApi
import com.and.data.api.article.GetArticlesByDateApi
import com.and.data.api.article.GetBookmarkedArticlesApi
import com.and.data.api.article.GetBookmarkedInterestsApi
import com.and.data.api.article.GetReceivedArticlesCountApi
import com.and.data.api.article.GetTodayArticlesApi
import com.and.data.api.article.PatchBookmarkArticleApi
import com.and.data.mapper.ArticleMapper
import com.and.data.mapper.BookmarkedArticlesMapper
import com.and.data.mapper.DailyArticleStatusMapper
import com.and.data.model.request.PatchBookmarkArticleRequestDto
import com.and.domain.model.Article
import com.and.domain.model.BookmarkedArticles
import com.and.domain.model.DailyArticle
import com.and.domain.model.DailyArticleStatus
import com.and.domain.model.type.ArticleStatus
import com.and.domain.model.type.InterestCategory
import com.and.domain.repository.ArticleRepository
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val getArticleStatusApi: GetArticleStatusApi,
    private val getArticlesByDateApi: GetArticlesByDateApi,
    private val getBookmarkedArticlesApi: GetBookmarkedArticlesApi,
    private val getBookmarkedInterestsApi: GetBookmarkedInterestsApi,
    private val getArticleByIdApi: GetArticleByIdApi,
    private val getTodayArticlesApi: GetTodayArticlesApi,
    private val patchBookmarkArticleApi: PatchBookmarkArticleApi,
    private val articleMapper: ArticleMapper,
    private val dailyArticleStatusMapper: DailyArticleStatusMapper,
    private val bookmarkedArticlesMapper: BookmarkedArticlesMapper,
    private val getReceivedArticlesCountApi: GetReceivedArticlesCountApi
): ArticleRepository, BaseRepository() {
    override suspend fun getMonthlyArticleStatus(year: Int, month: Int): List<DailyArticleStatus> {
        return handleApiCall(
            apiCall = {
                getArticleStatusApi.getArticles(
                    year = year.toString(),
                    month = month.toString()
                )
            },
            mapper = {
                it.data.map { article ->
                    DailyArticleStatus(
                        publishDate = article.publishDate,
                        hasArticles = article.hasArticles,
                        totalCount = article.totalCount,
                        unreadCount = article.unreadCount
                    )
                }
            }
        )
    }

    override suspend fun getArticlesByDate(
        year: Int,
        month: Int,
        day: Int
    ): List<Article> {
        return handleApiCall(
            apiCall = {
                getArticlesByDateApi.getArticles(
                    year = year.toString(),
                    month = month.toString(),
                    day = day.toString()
                )
            },
            mapper = {
                it.map { article ->
                    Article(
                        brandName = article.newsletter.brandName,
                        imageUrl = article.newsletter.imageUrl,
                        title = article.title,
                        articleId = article.id,
                        status = ArticleStatus.UNREAD
                    )
                }
            }
        )
    }

    override suspend fun getBookmarkedArticles(interest: InterestCategory): BookmarkedArticles {
        return handleApiCall(
            apiCall = {
                getBookmarkedArticlesApi.getBookmarkedArticles(interest.id.toString())
            },
            mapper = { response ->
                bookmarkedArticlesMapper.mapToDomain(response.data)
            }
        )
    }

    override suspend fun getBookmarkedInterests(): List<InterestCategory> {
        return handleApiCall(
            apiCall = {
                getBookmarkedInterestsApi.getBookmarkedInterests()
            },
            mapper = { response ->
                response.data.mapNotNull { interest ->
                    InterestCategory.getInterestByValue(interest.name)
                }
            }
        )
    }

    /**
     * TODO
     * (1) articleId가 어디서는 String, 어디서는 Int인 이유는?
     * (2) 아티클 리스트 조회와 특정 아티클 조회 응답 객체 구조가 다른 이유는?
     */
    override suspend fun getArticleById(articleId: Int): Article {
        return handleApiCall(
            apiCall = {
                getArticleByIdApi.getReadArticle(articleId = articleId.toString())
            },
            mapper = { response ->
                val data = response.data
                Article(
                    brandName = data.brandName,
                    imageUrl = data.brandImageUrl,
                    title = data.articleTitle,
                    articleId = data.articleId.toInt(),
                    status = ArticleStatus.UNREAD
                )
            }
        )
    }

    override suspend fun getTodayArticles(): List<Article> {
        return handleApiCall(
            apiCall = {
                getTodayArticlesApi.getTodayArticles()
            },
            mapper = { response ->
                response.receivedArticleList.map {
                    articleMapper.mapToDomain(it)
                }
            }
        )
    }

    /**
     * TODO
     * 아웃풋을 어떻게 소비할 건지 결정하기. Toast 출력?
     */
    override suspend fun updateBookmark(articleId: Int) {
        return handleApiCall(
            apiCall = {
                patchBookmarkArticleApi.postBookmarkArticle(
                    PatchBookmarkArticleRequestDto(
                        articleId = articleId
                    )
                )
            },
            mapper = { response ->
                response.message
            }
        )
    }

    override suspend fun getReceivedArticlesCount(): Int {
        return handleApiCall(
            apiCall = {
                getReceivedArticlesCountApi.getArticles()
            },
            mapper = { response ->
                response.count
            }
        )
    }
}