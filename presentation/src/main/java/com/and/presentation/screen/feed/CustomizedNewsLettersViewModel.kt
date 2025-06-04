package com.and.presentation.screen.feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.usecase.newsletter.member.GetRecommendedNewsLettersUseCase
import com.and.domain.util.ApiException
import com.and.presentation.mapper.RecommendedNewsLetterMapper
import com.and.presentation.model.RecommendedNewsLetterModel
import com.and.presentation.model.RecommendedNewsLettersModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    init {
        getCustomizedNewsLetters()
    }

    fun getCustomizedNewsLetters() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getRecommendedNewsLettersUseCase(Unit)
            }.onSuccess { (intersection, union) ->
                val data = RecommendedNewsLettersModel(
                    intersection = intersection
                        .shuffled()
                        .take(5)
                        .map { recommendedNewsLetterMapper.mapToPresentation(it) },
                    union = union
                        .shuffled()
                        .take(6)
                        .map { recommendedNewsLetterMapper.mapToPresentation(it) }
                )
                _customizedNewsLettersUiState.value = UiState.Success(data)
            }.onFailure { error ->
                error.printStackTrace()
                val message = (error as? ApiException)?.message ?: error.localizedMessage
                _customizedNewsLettersUiState.value = UiState.Error(message)
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