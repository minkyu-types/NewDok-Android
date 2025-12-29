package com.and.data.mapper

import com.and.data.model.data.ArticleDto
import com.and.data.model.data.NewsLetterDto
import com.and.data.model.data.UserDto
import com.and.data.model.response.GetArticlesByDateResponseDto
import com.and.data.model.response.GetArticlesByDateResponseDto.DailyArticleDto
import com.and.data.model.response.GetArticlesResponseDto.DailyArticleStatusDto
import com.and.data.model.response.GetBookmarkedArticlesResponseDto.BookmarkedArticleData
import com.and.data.model.response.GetBookmarkedArticlesResponseDto.BookmarkedArticleDto
import com.and.data.model.response.GetBookmarkedArticlesResponseDto.MonthlyBookmarkedArticlesDto
import com.and.data.model.response.GetNewsLettersResponseDto.NewsLetterDetailDto
import com.and.data.model.response.GetRecommendedNewsLettersResponseDto.RecommendedNewsLetterDto
import com.and.data.model.response.GetSubscribedNewsLettersResponseDto
import com.and.domain.model.Article
import com.and.domain.model.BookmarkedArticle
import com.and.domain.model.BookmarkedArticles
import com.and.domain.model.BriefNewsLetter
import com.and.domain.model.DailyArticle
import com.and.domain.model.DailyArticleStatus
import com.and.domain.model.MonthlyBookmarkedArticles
import com.and.domain.model.NewsLetter
import com.and.domain.model.RecommendedNewsLetter
import com.and.domain.model.User

interface DailyArticleStatusMapper: BaseMapper<DailyArticleStatus, DailyArticleStatusDto>
interface ArticleMapper: BaseMapper<Article, ArticleDto>

interface BookmarkedArticlesMapper: BaseMapper<BookmarkedArticles, BookmarkedArticleData>
interface MonthlyBookmarkedArticlesMapper: BaseMapper<MonthlyBookmarkedArticles, MonthlyBookmarkedArticlesDto>
interface BookmarkedArticleMapper: BaseMapper<BookmarkedArticle, BookmarkedArticleDto>

interface UserMapper: BaseMapper<User, UserDto>

interface NewsLetterMapper: BaseMapper<NewsLetter, NewsLetterDto>
interface BriefNewsLetterMapper: BaseMapper<BriefNewsLetter, GetSubscribedNewsLettersResponseDto.BriefNewsLetterDto>
interface NewsLetterDetailMapper: BaseMapper<NewsLetter, NewsLetterDetailDto>
interface RecommendedNewsLetterMapper: BaseMapper<RecommendedNewsLetter, RecommendedNewsLetterDto>