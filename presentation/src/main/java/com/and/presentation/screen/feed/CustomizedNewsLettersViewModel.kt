package com.and.presentation.screen.feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.type.RecommendedNewsLetterType
import com.and.domain.usecase.newsletter.member.GetRecommendedNewsLettersUseCase
import com.and.domain.util.ApiException
import com.and.presentation.mapper.RecommendedNewsLetterMapper
import com.and.presentation.model.RecommendedNewsLetterModel
import com.and.presentation.model.RecommendedNewsLettersModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomizedNewsLettersViewModel @Inject constructor(
    private val getRecommendedNewsLettersUseCase: GetRecommendedNewsLettersUseCase,
    private val recommendedNewsLetterMapper: RecommendedNewsLetterMapper
) : ViewModel() {
    private var rawUnion: List<RecommendedNewsLetterModel> = emptyList()

    private val _customizedNewsLettersUiState =
        mutableStateOf<UiState<RecommendedNewsLettersModel>>(UiState.Idle)
    val customizedNewsLettersUiState: State<UiState<RecommendedNewsLettersModel>> =
        _customizedNewsLettersUiState

    companion object {
        private const val ERROR_MESSAGE_CUSTOMIZED_NEWSLETTERS = "뉴스레터 조회 중 오류가 발생했습니다"
    }

    init {
        getCustomizedNewsLetters()
    }

    private fun getCustomizedNewsLetters() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                awaitAll(
                    async { getRecommendedNewsLettersUseCase(RecommendedNewsLetterType.UNION) },
                    async { getRecommendedNewsLettersUseCase(RecommendedNewsLetterType.INTERSECTION) }
                )
            }.onSuccess { (union, intersection) ->
                _customizedNewsLettersUiState.value = UiState.Success(
                    RecommendedNewsLettersModel(
                        union = union.map { recommendedNewsLetterMapper.mapToPresentation(it) },
                        intersection = intersection.map { recommendedNewsLetterMapper.mapToPresentation(it) }
                    )
                )
            }.onFailure { error ->
                error.printStackTrace()
                _customizedNewsLettersUiState.value =
                    UiState.Error(ERROR_MESSAGE_CUSTOMIZED_NEWSLETTERS)
            }
        }
    }

    fun refreshUnionOnly() {
        val currentState = _customizedNewsLettersUiState.value
        if (currentState is UiState.Success<*>) {
            val newUnion = rawUnion
                .shuffled()
                .take(6)

            val newData = RecommendedNewsLettersModel(
                intersection = (currentState.data as RecommendedNewsLettersModel).intersection,
                union = newUnion
            )
            _customizedNewsLettersUiState.value = UiState.Success(newData)
        }
    }
}