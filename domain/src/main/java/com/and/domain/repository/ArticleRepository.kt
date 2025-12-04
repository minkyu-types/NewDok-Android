package com.and.domain.repository

import com.and.domain.model.Article
import com.and.domain.model.BookmarkedArticles
import com.and.domain.model.DailyArticle
import com.and.domain.model.type.InterestCategory

interface ArticleRepository {
    suspend fun getArticles(year: Int, month: Int): List<DailyArticle>
    suspend fun getBookmarkedArticles(interest: InterestCategory): BookmarkedArticles
    suspend fun getBookmarkedInterests(): List<InterestCategory>
    suspend fun getArticleById(articleId: Int): Article
    suspend fun getTodayArticles(): List<Article>
    suspend fun updateBookmark(articleId: Int)
    suspend fun getReceivedArticlesCount(): Int
}