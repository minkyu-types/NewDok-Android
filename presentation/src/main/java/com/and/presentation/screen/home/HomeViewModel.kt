package com.and.presentation.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.Article
import com.and.domain.usecase.article.GetArticlesUseCase
import com.and.domain.util.ApiException
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase,
): ViewModel() {

    private val _articlesUiState = mutableStateOf<UiState<List<Article>>>(UiState.Idle)
    val articlesUiState: State<UiState<List<Article>>> = _articlesUiState

    fun getArticlesByYearMonth(
        year: Int,
        month: Int,
        date: Int,
    ) {
        viewModelScope.launch {
            _articlesUiState.value = UiState.Loading

            runCatching {
                withContext(Dispatchers.IO) {
                    getArticlesUseCase(
                        GetArticlesUseCase.GetArticlesParams(
                            year = year,
                            month = month
                        )
                    ).filter { article ->
                        article.publishDate == date
                    }
                }
            }.onSuccess { articles ->
                _articlesUiState.value = UiState.Success(articles)
            }.onFailure { error ->
                error.printStackTrace()
                val message = (error as? ApiException)?.message ?: error.localizedMessage
                _articlesUiState.value = UiState.Error(message)
            }
        }
    }
}