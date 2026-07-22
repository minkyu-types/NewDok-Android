package com.and.presentation.screen.articledetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.ArticleDetail
import com.and.domain.usecase.article.GetArticleByIdUseCase
import com.and.domain.util.ApiException
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val getArticleByIdUseCase: GetArticleByIdUseCase
): ViewModel() {
    private val _articleDetailUiState = mutableStateOf<UiState<ArticleDetail>>(UiState.Idle)
    val articleDetailUiState: State<UiState<ArticleDetail>> = _articleDetailUiState

    fun getArticleDetail(articleId: Int) {
        viewModelScope.launch {
            _articleDetailUiState.value = UiState.Loading

            try {
                val result = getArticleByIdUseCase(
                    GetArticleByIdUseCase.GetArticleByIdParams(articleId)
                )
                _articleDetailUiState.value = UiState.Success(result)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                e.printStackTrace()
                val message = (e as? ApiException)?.message ?: e.localizedMessage
                _articleDetailUiState.value = UiState.Error(message)
            }
        }
    }
}
