package com.and.presentation.screen.subscription

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.BriefNewsLetter
import com.and.domain.usecase.newsletter.member.GetSubscribedNewsLettersUseCase
import com.and.domain.usecase.newsletter.member.GetUnSubscribedNewsLettersUseCase
import com.and.domain.util.ApiException
import com.and.presentation.mapper.BriefNewsLetterMapper
import com.and.presentation.mapper.NewsLetterMapper
import com.and.presentation.model.BriefNewsLetterModel
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SubscriptionViewModel @Inject constructor(
    private val getSubscribedNewsLettersUseCase: GetSubscribedNewsLettersUseCase,
    private val getUnSubscribedNewsLettersUseCase: GetUnSubscribedNewsLettersUseCase,
    private val briefNewsLetterMapper: BriefNewsLetterMapper
) : ViewModel() {

    private val _subscribedUiState = mutableStateOf<UiState<List<BriefNewsLetterModel>>>(UiState.Idle)
    val subscribedUiState: State<UiState<List<BriefNewsLetterModel>>> = _subscribedUiState

    fun getSubscribedNewsLetters() {
        viewModelScope.launch {
            _subscribedUiState.value = UiState.Loading

            kotlin.runCatching {
                val newsLetters = withContext(Dispatchers.IO) {
                    getSubscribedNewsLettersUseCase(Unit)
                }
                newsLetters.map {
                    briefNewsLetterMapper.mapToPresentation(it)
                }
            }.onSuccess { newsLetters ->
                _subscribedUiState.value = UiState.Success(newsLetters)
            }.onFailure { error ->
                error.printStackTrace()
                val message = (error as? ApiException)?.message ?: error.localizedMessage
                _subscribedUiState.value = UiState.Error(message)
            }
        }
    }

    fun getUnsubscribedNewsLetters() {
        viewModelScope.launch {
            _subscribedUiState.value = UiState.Loading

            kotlin.runCatching {
                val newsLetters = getUnSubscribedNewsLettersUseCase(Unit)
                newsLetters.map {
                    briefNewsLetterMapper.mapToPresentation(it)
                }
            }.onSuccess { newsLetters ->
                _subscribedUiState.value = UiState.Success(newsLetters)
            }.onFailure { error ->
                error.printStackTrace()
                val message = (error as? ApiException)?.message ?: error.localizedMessage
                _subscribedUiState.value = UiState.Error(message)
            }
        }
    }
}