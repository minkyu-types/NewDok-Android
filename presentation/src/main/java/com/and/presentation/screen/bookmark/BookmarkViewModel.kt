package com.and.presentation.screen.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.type.InterestCategory
import com.and.domain.usecase.article.GetBookmarkedArticlesUseCase
import com.and.presentation.mapper.BookmarkedArticlesMapper
import com.and.presentation.model.bookmarkedarticle.BookmarkedArticlesModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getBookmarkedArticlesUseCase: GetBookmarkedArticlesUseCase,
    private val bookmarkedArticlesMapper: BookmarkedArticlesMapper
) : ViewModel() {
    var selectedInterests by mutableStateOf(setOf(InterestCategory.INTEREST_ALL))
        private set

    private val _interestedArticlesUiState = mutableStateOf<UiState<BookmarkedArticlesModel>>(UiState.Idle)
    val interestedArticlesUiState: State<UiState<BookmarkedArticlesModel>> = _interestedArticlesUiState

    init {
        fetchAndMergeBookmarkedArticles()
    }

    private fun fetchAndMergeBookmarkedArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            _interestedArticlesUiState.value = UiState.Loading

            try {
                val interestsToFetch = if (selectedInterests == setOf(InterestCategory.INTEREST_ALL)) {
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
                val finalResults = BookmarkedArticlesModel(
                    totalAmount = mergedResults.sumOf { it.totalAmount },
                    bookmarkForMonth = mergedResults.flatMap { it.bookmarkForMonth }
                )
                _interestedArticlesUiState.value = UiState.Success(finalResults)
            } catch (e: Exception) {
                e.printStackTrace()
                _interestedArticlesUiState.value = UiState.Error(e.message ?: "")
            }
        }
    }

    fun toggleInterest(interest: InterestCategory) {
        selectedInterests = when(interest) {
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
}