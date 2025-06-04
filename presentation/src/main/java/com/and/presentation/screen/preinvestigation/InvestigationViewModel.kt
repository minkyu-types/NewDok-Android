package com.and.presentation.screen.preinvestigation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.InterestCategory
import com.and.domain.usecase.user.GetPreInvestigateNewsLettersUseCase
import com.and.domain.usecase.user.GetUserInfoUseCase
import com.and.domain.usecase.user.UpdateUserIndustryUseCase
import com.and.domain.usecase.user.UpdateUserInterestsUseCase
import com.and.presentation.mapper.NewsLetterMapper
import com.and.presentation.model.NewsLetterModel
import com.and.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvestigationViewModel @Inject constructor(
    private val updateInterestsUseCase: UpdateUserInterestsUseCase,
    private val updateIndustryUseCase: UpdateUserIndustryUseCase,
    private val getPreInvestigateNewsLettersUseCase: GetPreInvestigateNewsLettersUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val newsLetterMapper: NewsLetterMapper
) : ViewModel() {

    private var selectedIndustry = IndustryCategory.DEFAULT

    private val _selectedInterests = MutableStateFlow<Set<InterestCategory>>(emptySet())
    val selectedInterests: StateFlow<Set<InterestCategory>> = _selectedInterests

    private val _preInvestigateNewsLettersUiState =
        mutableStateOf<UiState<List<NewsLetterModel>>>(UiState.Idle)
    val preInvestigateNewsLettersUiState: State<UiState<List<NewsLetterModel>>> =
        _preInvestigateNewsLettersUiState

    private val _nickname = MutableStateFlow<String?>(null)
    val nickname: StateFlow<String?> = _nickname

    fun toggleInterest(category: InterestCategory) {
        _selectedInterests.value = _selectedInterests.value
            .toMutableSet()
            .also { set ->
                if (category in set) set.remove(category) else set.add(category)
            }
    }

    fun updateInterests(
        interests: Set<InterestCategory>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            updateInterestsUseCase(
                UpdateUserInterestsUseCase.UpdateUserInterestsParams(
                    interests
                )
            )
        }
    }

    fun updateIndustry(
        industry: IndustryCategory
    ) {
        selectedIndustry = industry
        viewModelScope.launch(Dispatchers.IO) {
            updateIndustryUseCase(
                UpdateUserIndustryUseCase.UpdateUserIndustryParams(
                    industry.id
                )
            )
        }
    }

    fun loadUserInfo() {
        viewModelScope.launch {
            runCatching {
                getUserInfoUseCase(Unit)
            }.onSuccess { result ->
                _nickname.value = result.nickname
            }.onFailure { error ->
                error.printStackTrace()
                _nickname.value = null
            }
        }
    }

    fun getPreInvestigationNewsLetters() {
        viewModelScope.launch(Dispatchers.IO) {
            _preInvestigateNewsLettersUiState.value = UiState.Loading

            runCatching {
                getPreInvestigateNewsLettersUseCase(
                    GetPreInvestigateNewsLettersUseCase.GetPreInvestigateNewsLettersParams(
                        industry = selectedIndustry,
                        interests = _selectedInterests.value.toList()
                    )
                )
            }.onSuccess { result ->
                val newsLetters = result.map {
                    newsLetterMapper.mapToPresentation(it)
                }
                _preInvestigateNewsLettersUiState.value = UiState.Success(newsLetters)
            }.onFailure { error ->
                error.printStackTrace()
                _preInvestigateNewsLettersUiState.value = UiState.Error(error.message ?: "")
            }
        }
    }
}