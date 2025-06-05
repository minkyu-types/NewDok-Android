package com.and.presentation.screen.subscription

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.usecase.newsletter.member.GetSubscribedNewsLettersUseCase
import com.and.domain.usecase.newsletter.member.GetUnSubscribedNewsLettersUseCase
import com.and.domain.usecase.newsletter.member.UpdateSubscriptionUseCase
import com.and.domain.util.ApiException
import com.and.presentation.mapper.BriefNewsLetterMapper
import com.and.presentation.model.BriefNewsLetterModel
import com.and.presentation.util.CommonUiEvent
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SubscriptionViewModel @Inject constructor(
    private val getSubscribedNewsLettersUseCase: GetSubscribedNewsLettersUseCase,
    private val getUnSubscribedNewsLettersUseCase: GetUnSubscribedNewsLettersUseCase,
    private val updateSubscriptionUseCase: UpdateSubscriptionUseCase,
    private val briefNewsLetterMapper: BriefNewsLetterMapper
) : ViewModel() {

    private val _eventChannel = Channel<CommonUiEvent>(Channel.BUFFERED)
    val eventChannel = _eventChannel.receiveAsFlow()

    private val _subscribedUiState = mutableStateOf<UiState<List<BriefNewsLetterModel>>>(UiState.Idle)
    val subscribedUiState: State<UiState<List<BriefNewsLetterModel>>> = _subscribedUiState

    fun updateSubscription(
        newsLetterId: Int,
        wasSubscribed: Boolean
    ) {
        viewModelScope.launch {
            runCatching {
                updateSubscriptionUseCase(
                    UpdateSubscriptionUseCase.UpdateSubscriptionParams(
                        newsLetterId = newsLetterId,
                        wasSubscribed = wasSubscribed
                    )
                )
            }.onSuccess {
                _eventChannel.send(CommonUiEvent.ShowToast("북마크 변경 완료"))
                withContext(Dispatchers.Main) {
                    val currentState = _subscribedUiState.value
                    if (currentState is UiState.Success) {
                        val updatedList = currentState.data.filterNot { it.id == newsLetterId }
                        _subscribedUiState.value = UiState.Success(updatedList)
                    }
                }
            }.onFailure { error ->
                error.printStackTrace()
            }
        }
    }

    fun getSubscribedNewsLetters() {
        viewModelScope.launch {
            _subscribedUiState.value = UiState.Loading

            runCatching {
                val newsLetters = getSubscribedNewsLettersUseCase(Unit)
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

            runCatching {
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