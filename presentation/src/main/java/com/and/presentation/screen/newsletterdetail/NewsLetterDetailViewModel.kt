package com.and.presentation.screen.newsletterdetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.usecase.newsletter.member.GetNewsLetterByIdUseCase
import com.and.domain.usecase.newsletter.member.UpdateSubscriptionUseCase
import com.and.domain.util.ApiException
import com.and.presentation.mapper.NewsLetterDetailMapper
import com.and.presentation.model.NewsLetterDetailModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsLetterDetailViewModel @Inject constructor(
    private val getNewsLetterByIdUseCase: GetNewsLetterByIdUseCase,
    private val updateSubscriptionUseCase: UpdateSubscriptionUseCase,
    private val newsLetterDetailMapper: NewsLetterDetailMapper
): ViewModel() {
    private val _newsLetterDetailUiState = mutableStateOf<UiState<NewsLetterDetailModel>>(UiState.Idle)
    val newsLetterDetailUiState: State<UiState<NewsLetterDetailModel>> = _newsLetterDetailUiState

    fun getNewsLetterDetail(
        id: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _newsLetterDetailUiState.value = UiState.Loading
            
            runCatching {
                getNewsLetterByIdUseCase(
                    GetNewsLetterByIdUseCase.GetNewsLetterByIdParams(
                        id
                    )
                )
            }.onSuccess { result ->
                val data = newsLetterDetailMapper.mapToPresentation(result)
                _newsLetterDetailUiState.value = UiState.Success(data)
            }.onFailure { error ->
                error.printStackTrace()
                val message = (error as? ApiException)?.message ?: error.localizedMessage
                _newsLetterDetailUiState.value = UiState.Error(message)
            }
        }
    }

    fun updateSubscription(
        newsLetterId: Int,
        wasSubscribed: Boolean
    ) {
        viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    updateSubscriptionUseCase(
                        UpdateSubscriptionUseCase.UpdateSubscriptionParams(
                            newsLetterId, wasSubscribed
                        )
                    )
                }
            }.onSuccess { result ->

            }.onFailure { error ->
                error.printStackTrace()
            }
        }
    }
}