package com.and.presentation.mapper

import com.and.domain.model.BookmarkedArticle
import com.and.domain.model.BookmarkedArticles
import com.and.domain.model.BriefNewsLetter
import com.and.domain.model.DailyArticleStatus
import com.and.domain.model.MonthlyBookmarkedArticles
import com.and.domain.model.NewsLetter
import com.and.domain.model.RecommendedNewsLetter
import com.and.domain.model.User
import com.and.presentation.model.BriefNewsLetterModel
import com.and.presentation.model.DailyArticleModel
import com.and.presentation.model.NewsLetterDetailModel
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.model.NewsLetterSubscriptionModel
import com.and.presentation.model.RecommendedNewsLetterModel
import com.and.presentation.model.UserModel
import com.and.presentation.model.bookmarkedarticle.BookmarkedArticleModel
import com.and.presentation.model.bookmarkedarticle.BookmarkedArticlesModel
import com.and.presentation.model.bookmarkedarticle.MonthlyBookmarkedArticlesModel

interface NewsLetterMapper: BaseMapper<NewsLetter, NewsLetterModel>
interface NewsLetterDetailMapper: BaseMapper<NewsLetter, NewsLetterDetailModel>
interface NewsLetterSubscriptionMapper: BaseMapper<NewsLetter, NewsLetterSubscriptionModel>
interface RecommendedNewsLetterMapper: BaseMapper<RecommendedNewsLetter, RecommendedNewsLetterModel>
interface BriefNewsLetterMapper: BaseMapper<BriefNewsLetter, BriefNewsLetterModel>

interface BookmarkedArticleMapper: BaseMapper<BookmarkedArticle, BookmarkedArticleModel>
interface MonthlyBookmarkedArticlesMapper: BaseMapper<MonthlyBookmarkedArticles, MonthlyBookmarkedArticlesModel>
interface BookmarkedArticlesMapper: BaseMapper<BookmarkedArticles, BookmarkedArticlesModel>

interface UserMapper: BaseMapper<User, UserModel>

