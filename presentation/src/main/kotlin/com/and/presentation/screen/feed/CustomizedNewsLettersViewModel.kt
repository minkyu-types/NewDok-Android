package com.and.presentation.screen.feed

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.type.RecommendedNewsLetterType
import com.and.domain.usecase.newsletter.member.GetRecommendedNewsLettersUseCase
import com.and.domain.util.ApiException
import com.and.newdok.presentation.BuildConfig
import com.and.presentation.mapper.RecommendedNewsLetterMapper
import com.and.presentation.model.RecommendedNewsLettersModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
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

    private val _industryNotSelectedEvent = MutableSharedFlow<Unit>()
    val industryNotSelectedEvent: SharedFlow<Unit> = _industryNotSelectedEvent

    private val _interestNotSelectedEvent = MutableSharedFlow<Unit>()
    val interestNotSelectedEvent: SharedFlow<Unit> = _interestNotSelectedEvent

    companion object {
        private const val TAG = "CustomizedNewsLetters"
        private const val ERROR_MESSAGE_CUSTOMIZED_NEWSLETTERS = "뉴스레터 조회 중 오류가 발생했습니다"
        private const val ERROR_MESSAGE_INDUSTRY_NOT_SELECTED = "종사산업 미선택"
        private const val ERROR_MESSAGE_INTEREST_NOT_SELECTED = "관심사 미선택"
    }

    private var initialized = false

    fun initialize() {
        if (!initialized) {
            initialized = true
            getCustomizedNewsLetters()
        }
    }

    fun resetAndReload() {
        initialized = false
        initialize()
    }

    private fun getCustomizedNewsLetters() {
        viewModelScope.launch {
            runCatching {
                coroutineScope {
                    awaitAll(
                        async { getRecommendedNewsLettersUseCase(RecommendedNewsLetterType.UNION) },
                        async { getRecommendedNewsLettersUseCase(RecommendedNewsLetterType.INTERSECTION) }
                    )
                }
            }.onSuccess { (union, intersection) ->
                val mappedUnion = union.map { recommendedNewsLetterMapper.mapToPresentation(it) }
                _customizedNewsLettersUiState.value = UiState.Success(
                    RecommendedNewsLettersModel(
                        union = mappedUnion,
                        intersection = intersection.map { recommendedNewsLetterMapper.mapToPresentation(it) }
                    )
                )
            }.onFailure { error ->
                // 사용자에게는 고정 문구만 보여주므로, 실제 원인은 여기서 반드시 남긴다.
                // 파싱 실패(JsonDataException)인지 서버 응답 오류인지가 여기서만 구분된다.
                val apiStatus = (error as? ApiException)?.statusCode
                Log.e(TAG, "추천 뉴스레터 조회 실패 (statusCode=$apiStatus)", error)

                val apiMessage = (error as? ApiException)?.message

                when {
                    apiMessage?.contains(ERROR_MESSAGE_INDUSTRY_NOT_SELECTED) == true ->
                        _industryNotSelectedEvent.emit(Unit)

                    apiMessage?.contains(ERROR_MESSAGE_INTEREST_NOT_SELECTED) == true ->
                        _interestNotSelectedEvent.emit(Unit)

                    else ->
                        _customizedNewsLettersUiState.value = UiState.Error(errorMessageOf(error))
                }
            }
        }
    }

    /** 릴리즈 사용자에게 보이는 문구는 그대로 두고, 디버그 빌드에서만 실제 원인을 덧붙인다 */
    private fun errorMessageOf(error: Throwable): String =
        if (BuildConfig.DEBUG) {
            "$ERROR_MESSAGE_CUSTOMIZED_NEWSLETTERS\n(${error::class.simpleName}: ${error.message})"
        } else {
            ERROR_MESSAGE_CUSTOMIZED_NEWSLETTERS
        }

    fun refresh() {
        getCustomizedNewsLetters()
    }
}