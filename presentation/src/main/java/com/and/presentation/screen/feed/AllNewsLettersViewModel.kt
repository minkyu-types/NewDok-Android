package com.and.presentation.screen.feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.SortCategory
import com.and.domain.usecase.newsletter.member.GetNewsLettersUseCase
import com.and.domain.util.ApiException
import com.and.presentation.mapper.NewsLetterMapper
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class AllNewsLettersViewModel @Inject constructor(
    private val getAllNewsLettersUseCase: GetNewsLettersUseCase,
    private val newsLetterMapper: NewsLetterMapper
): ViewModel() {

    private val _allNewsLettersUiState = mutableStateOf<UiState<List<NewsLetterModel>>>(UiState.Idle)
    val allNewsLettersUiState: State<UiState<List<NewsLetterModel>>> = _allNewsLettersUiState

    fun getAllNewsLetters(
        orderOption: SortCategory,
        industry: IndustryCategory,
        date: ZonedDateTime
    ) {
        viewModelScope.launch {

            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    getAllNewsLettersUseCase(
                        GetNewsLettersUseCase.GetNewsLettersParams(orderOption, industry, date)
                    ).map {
                        newsLetterMapper.mapToPresentation(it)
                    }
                }
            }.onSuccess { newsLetters ->
                _allNewsLettersUiState.value = UiState.Success(newsLetters)
            }.onFailure { error ->
                error.printStackTrace()
                val message = (error as? ApiException)?.message ?: error.localizedMessage
                _allNewsLettersUiState.value = UiState.Error(message)
            }
        }
    }
}