package com.and.domain.repository

import com.and.domain.model.Article
import com.and.domain.model.BookmarkedArticles
import com.and.domain.model.DailyArticles
import com.and.domain.model.type.InterestCategory

interface ArticleRepository {
    fun getArticles(year: Int, month: Int): List<DailyArticles>
    fun getBookmarkedArticles(interest: InterestCategory): BookmarkedArticles
    fun getBookmarkedInterests(): List<InterestCategory>
    fun getArticleById(articleId: Int): Article
    fun searchArticle(keyword: String): Article
    fun getTodayArticles(): List<Article>
    fun updateBookmark(articleId: Int)
}