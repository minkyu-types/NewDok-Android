package com.and.data.mapper

import com.and.data.model.data.ArticleDto
import com.and.data.model.data.NewsLetterDto
import com.and.data.model.data.UserDto
import com.and.data.model.response.GetArticlesResponseDto.DailyArticlesDto
import com.and.data.model.response.GetBookmarkedArticlesResponseDto.BookmarkedArticleData
import com.and.data.model.response.GetBookmarkedArticlesResponseDto.BookmarkedArticleDto
import com.and.data.model.response.GetBookmarkedArticlesResponseDto.MonthlyBookmarkedArticlesDto
import com.and.data.model.response.GetNewsLettersResponseDto.NewsLetterDetailDto
import com.and.domain.model.Article
import com.and.domain.model.BookmarkedArticle
import com.and.domain.model.BookmarkedArticles
import com.and.domain.model.DailyArticles
import com.and.domain.model.MonthlyBookmarkedArticles
import com.and.domain.model.NewsLetter
import com.and.domain.model.User

interface DailyArticleMapper: BaseMapper<DailyArticles, DailyArticlesDto>
interface ArticleMapper: BaseMapper<Article, ArticleDto>

interface BookmarkedArticlesMapper: BaseMapper<BookmarkedArticles, BookmarkedArticleData>
interface MonthlyBookmarkedArticlesMapper: BaseMapper<MonthlyBookmarkedArticles, MonthlyBookmarkedArticlesDto>
interface BookmarkedArticleMapper: BaseMapper<BookmarkedArticle, BookmarkedArticleDto>

interface UserMapper: BaseMapper<User, UserDto>

interface NewsLetterMapper: BaseMapper<NewsLetter, NewsLetterDto>
interface NewsLetterDetailMapper: BaseMapper<NewsLetter, NewsLetterDetailDto>