package com.and.presentation.screen.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.type.ArticleSortCategory
import com.and.domain.model.type.InterestCategory
import com.and.domain.usecase.article.GetBookmarkedArticlesUseCase
import com.and.presentation.mapper.BookmarkedArticlesMapper
import com.and.presentation.model.bookmarkedarticle.BookmarkedArticlesModel
import com.and.presentation.model.bookmarkedarticle.MonthlyBookmarkedArticlesModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getBookmarkedArticlesUseCase: GetBookmarkedArticlesUseCase,
    private val bookmarkedArticlesMapper: BookmarkedArticlesMapper
) : ViewModel() {
    var selectedInterests by mutableStateOf(setOf(InterestCategory.INTEREST_ALL))
        private set

    var currentSort by mutableStateOf(ArticleSortCategory.SORT_RECENT_ADDED)
        private set

    private val _interestedArticlesUiState =
        mutableStateOf<UiState<BookmarkedArticlesModel>>(UiState.Idle)
    val interestedArticlesUiState: State<UiState<BookmarkedArticlesModel>> =
        _interestedArticlesUiState

    init {
        fetchAndMergeBookmarkedArticles()
    }

    private fun fetchAndMergeBookmarkedArticles() {
        viewModelScope.launch {
            _interestedArticlesUiState.value = UiState.Loading

            runCatching {
                supervisorScope {
                    val interestsToFetch =
                        if (selectedInterests == setOf(InterestCategory.INTEREST_ALL)) {
                            listOf(InterestCategory.INTEREST_ALL)
                        } else {
                            selectedInterests.toList()
                        }

                    val deferredResults = interestsToFetch.map { interest ->
                        async {
                            getBookmarkedArticlesUseCase(
                                GetBookmarkedArticlesUseCase.GetBookmarkedArticlesParams(
                                    interest
                                )
                            )
                        }
                    }

                    val results = deferredResults.awaitAll()
                    val mergedResults = results
                        .map {
                            bookmarkedArticlesMapper.mapToPresentation(it)
                        }

                    val allMonthly = mergedResults.flatMap { it.bookmarkForMonth }
                    val deduplicatedMonthly = allMonthly
                        .groupBy { it.month }
                        .map { (month, groups) ->
                            val uniqueArticles = groups
                                .flatMap { it.articles }
                                .distinctBy { it.articleId }
                            MonthlyBookmarkedArticlesModel(
                                id = groups.first().id,
                                month = month,
                                articles = uniqueArticles
                            )
                        }

                    val sorted = applySorting(deduplicatedMonthly)
                    val totalAmount = sorted.sumOf { it.articles.size }

                    BookmarkedArticlesModel(
                        totalAmount = totalAmount,
                        bookmarkForMonth = sorted
                    )
                }
            }.onSuccess { result ->
                _interestedArticlesUiState.value = UiState.Success(result)
            }.onFailure { error ->
                error.printStackTrace()
                _interestedArticlesUiState.value = UiState.Error(error.message ?: "")
            }
        }
    }

    private fun applySorting(
        monthly: List<MonthlyBookmarkedArticlesModel>
    ): List<MonthlyBookmarkedArticlesModel> {
        return when (currentSort) {
            ArticleSortCategory.SORT_RECENT_ADDED -> monthly
            ArticleSortCategory.SORT_PUBLISH_DESCENDING -> {
                monthly.map { m ->
                    m.copy(articles = m.articles.sortedByDescending { it.date })
                }.sortedByDescending { it.month }
            }
            ArticleSortCategory.SORT_PUBLISH_ASCENDING -> {
                monthly.map { m ->
                    m.copy(articles = m.articles.sortedBy { it.date })
                }.sortedBy { it.month }
            }
        }
    }

    fun toggleInterest(interest: InterestCategory) {
        selectedInterests = when (interest) {
            InterestCategory.INTEREST_ALL -> setOf(InterestCategory.INTEREST_ALL)
            else -> {
                val withoutAll = selectedInterests - InterestCategory.INTEREST_ALL
                val toggled = if (withoutAll.contains(interest)) {
                    withoutAll - interest
                } else {
                    withoutAll + interest
                }
                toggled.ifEmpty {
                    setOf(InterestCategory.INTEREST_ALL)
                }
            }
        }
        fetchAndMergeBookmarkedArticles()
    }

    fun setSort(sort: ArticleSortCategory) {
        currentSort = sort
        fetchAndMergeBookmarkedArticles()
    }
}
