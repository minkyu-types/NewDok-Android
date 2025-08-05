package com.and.presentation.screen.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.PublicationDay
import com.and.domain.model.type.SortCategory
import com.and.domain.usecase.newsletter.member.GetNewsLettersUseCase
import com.and.presentation.mapper.NewsLetterMapper
import com.and.presentation.mapper.NewsLetterSubscriptionMapper
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.model.NewsLetterSubscriptionModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AllNewsLettersViewModel @Inject constructor(
    private val getAllNewsLettersUseCase: GetNewsLettersUseCase,
    private val newsLetterSubscriptionMapper: NewsLetterSubscriptionMapper
) : ViewModel() {
    private val _sortFlow = MutableStateFlow(SortCategory.POPULARITY)
    private val _industriesFlow = MutableStateFlow(listOf(IndustryCategory.DEFAULT))
    private val _daysFlow = MutableStateFlow(listOf(PublicationDay.MONDAY))

    fun setSort(sort: SortCategory) {
        _sortFlow.value = sort
    }

    fun setIndustries(list: List<IndustryCategory>) {
        _industriesFlow.value = list
    }

    fun setDays(list: List<PublicationDay>) {
        _daysFlow.value = list
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _newsLetterUiState = combine(
        _sortFlow,
        _industriesFlow,
        _daysFlow
    ) { sort, industries, days ->
        Triple(sort, industries, days)
    }.flatMapLatest { (sort, industries, days) ->
        flow {
            emit(UiState.Loading)
            val aggregated = industries.flatMap { industry ->
                days.flatMap { day ->
                    getAllNewsLettersUseCase(
                        GetNewsLettersUseCase.GetNewsLettersParams(sort, industry, day.dayId)
                    ).map { newsLetterSubscriptionMapper.mapToPresentation(it) }
                }
            }
            emit(UiState.Success(aggregated))
        }.flowOn(Dispatchers.IO)
            .catch { error ->
                error.printStackTrace()
                emit(UiState.Error(error.message ?: ""))
            }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = UiState.Idle
    )
    val newsLettersUiState: StateFlow<UiState<List<NewsLetterSubscriptionModel>>> =
        _newsLetterUiState
}