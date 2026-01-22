package com.and.presentation.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.usecase.article.GetArticleByIdUseCase
import com.and.domain.usecase.article.GetArticlesByDateUseCase
import com.and.domain.usecase.article.GetMonthlyArticleStatusUseCase
import com.and.domain.util.ApiException
import com.and.presentation.model.DailyArticleModel
import com.and.presentation.model.DailyArticleStatusModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMonthlyArticleStatusUseCase: GetMonthlyArticleStatusUseCase,
    private val getArticlesByDateUseCase: GetArticlesByDateUseCase
): ViewModel() {

    private val _monthlyArticleStateUiState = mutableStateOf<UiState<List<DailyArticleStatusModel>>>(UiState.Idle)
    val monthlyArticleStateUiState: State<UiState<List<DailyArticleStatusModel>>> = _monthlyArticleStateUiState

    private val _articlesUiState = mutableStateOf<UiState<List<DailyArticleModel>>>(UiState.Idle)
    val articlesUiState: State<UiState<List<DailyArticleModel>>> = _articlesUiState

    fun getArticleStatusByYearMonth(
        year: Int,
        month: Int,
        date: Int,
    ) {
        viewModelScope.launch {
            _monthlyArticleStateUiState.value = UiState.Loading

            runCatching {
                withContext(Dispatchers.IO) {
                    getMonthlyArticleStatusUseCase(
                        GetMonthlyArticleStatusUseCase.GetArticlesParams(
                            year = year,
                            month = month
                        )
                    ).filter { article ->
                        article.publishDate == date
                    }
                }
            }.onSuccess { articles ->
                _monthlyArticleStateUiState.value = UiState.Success(articles.map {
                    DailyArticleStatusModel(
                        publishDate = it.publishDate,
                        hasArticles = it.hasArticles,
                        totalCount = it.totalCount,
                        unreadCount = it.unreadCount,
                    )
                })
            }.onFailure { error ->
                error.printStackTrace()
                val message = (error as? ApiException)?.message ?: error.localizedMessage
                _monthlyArticleStateUiState.value = UiState.Error(message)
            }
        }
    }

    fun getArticlesByDate(
        year: Int,
        month: Int,
        date: Int,
    ) {
        viewModelScope.launch {
            _articlesUiState.value = UiState.Loading

            runCatching {
                withContext(Dispatchers.IO) {
                    getArticlesByDateUseCase(
                        GetArticlesByDateUseCase.GetArticlesByDateParams(
                            year = year,
                            month = month,
                            date = date
                        )
                    )
                }
            }.onSuccess { articles ->
                _articlesUiState.value = UiState.Success(articles.map {
                    DailyArticleModel(
                        brandName = it.brandName,
                        imageUrl = it.imageUrl,
                        articleTitle = it.title,
                        articleId = it.articleId,
                        status = it.status.name
                    )
                })
            }.onFailure { error ->
                error.printStackTrace()
                val message = (error as? ApiException)?.message ?: error.localizedMessage
                _monthlyArticleStateUiState.value = UiState.Error(message)
            }
        }
    }

}