package com.and.presentation.screen.feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.type.RecommendedNewsLetterType
import com.and.domain.usecase.newsletter.member.GetRecommendedNewsLettersUseCase
import com.and.presentation.mapper.RecommendedNewsLetterMapper
import com.and.presentation.model.RecommendedNewsLettersModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomizedNewsLettersViewModel @Inject constructor(
    private val getRecommendedNewsLettersUseCase: GetRecommendedNewsLettersUseCase,
    private val recommendedNewsLetterMapper: RecommendedNewsLetterMapper
) : ViewModel() {
    private val _customizedNewsLettersUiState =
        mutableStateOf<UiState<RecommendedNewsLettersModel>>(UiState.Idle)
    val customizedNewsLettersUiState: State<UiState<RecommendedNewsLettersModel>> =
        _customizedNewsLettersUiState

    companion object {
        private const val ERROR_MESSAGE_CUSTOMIZED_NEWSLETTERS = "뉴스레터 조회 중 오류가 발생했습니다"
    }

    private var initialized = false

    fun initialize() {
        if (!initialized) {
            initialized = true
            getCustomizedNewsLetters()
        }
    }

    private fun getCustomizedNewsLetters() {
        viewModelScope.launch {
            runCatching {
                awaitAll(
                    async { getRecommendedNewsLettersUseCase(RecommendedNewsLetterType.UNION) },
                    async { getRecommendedNewsLettersUseCase(RecommendedNewsLetterType.INTERSECTION) }
                )
            }.onSuccess { (union, intersection) ->
                val mappedUnion = union.map { recommendedNewsLetterMapper.mapToPresentation(it) }
                _customizedNewsLettersUiState.value = UiState.Success(
                    RecommendedNewsLettersModel(
                        union = mappedUnion,
                        intersection = intersection.map { recommendedNewsLetterMapper.mapToPresentation(it) }
                    )
                )
            }.onFailure {
                _customizedNewsLettersUiState.value =
                    UiState.Error(ERROR_MESSAGE_CUSTOMIZED_NEWSLETTERS)
            }
        }
    }

    fun refresh() {
        getCustomizedNewsLetters()
    }
}