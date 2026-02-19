package com.and.presentation.screen.preinvestigation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.Industry
import com.and.domain.model.Interest
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.InterestCategory
import com.and.domain.usecase.user.GetPreInvestigateNewsLettersUseCase
import com.and.domain.usecase.user.GetUserInfoUseCase
import com.and.domain.usecase.user.UpdateUserIndustryUseCase
import com.and.domain.usecase.user.UpdateUserInterestsUseCase
import com.and.domain.usecase.util.GetIndustriesUseCase
import com.and.domain.usecase.util.GetInterestsUseCase
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
    private val getIndustriesUseCase: GetIndustriesUseCase,
    private val getInterestsUseCase: GetInterestsUseCase,
    private val newsLetterMapper: NewsLetterMapper
) : ViewModel() {

    private var selectedIndustry = IndustryCategory.DEFAULT

    private val _industries = MutableStateFlow<List<Industry>>(emptyList())
    val industries: StateFlow<List<Industry>> = _industries

    private val _interestOptions = MutableStateFlow<List<Interest>>(emptyList())
    val interestOptions: StateFlow<List<Interest>> = _interestOptions

    private val _selectedInterests = MutableStateFlow<Set<Interest>>(emptySet())
    val selectedInterests: StateFlow<Set<Interest>> = _selectedInterests

    private val _preInvestigateNewsLettersUiState =
        mutableStateOf<UiState<List<NewsLetterModel>>>(UiState.Idle)
    val preInvestigateNewsLettersUiState: State<UiState<List<NewsLetterModel>>> =
        _preInvestigateNewsLettersUiState

    private val _nickname = MutableStateFlow<String?>(null)
    val nickname: StateFlow<String?> = _nickname

    fun toggleInterest(interest: Interest) {
        _selectedInterests.value = _selectedInterests.value
            .toMutableSet()
            .also { set ->
                if (interest in set) set.remove(interest) else set.add(interest)
            }
    }

    fun updateInterests(
        interests: Set<Interest>
    ) {
        val categories = interests.mapNotNull { interest ->
            InterestCategory.getInterestById(interest.id)
        }.toSet()
        viewModelScope.launch {
            updateInterestsUseCase(
                UpdateUserInterestsUseCase.UpdateUserInterestsParams(
                    categories
                )
            )
        }
    }

    fun updateIndustry(
        industry: Industry
    ) {
        selectedIndustry = IndustryCategory.getIndustryById(industry.id) ?: IndustryCategory.DEFAULT
        viewModelScope.launch {
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

    fun getIndustries() {
        viewModelScope.launch {
            runCatching {
                getIndustriesUseCase(Unit)
            }.onSuccess { result ->
                _industries.value = result
            }.onFailure { error ->
                error.printStackTrace()
                _industries.value = emptyList()
            }
        }
    }

    fun getInterests() {
        viewModelScope.launch {
            runCatching {
                getInterestsUseCase(Unit)
            }.onSuccess { result ->
                _interestOptions.value = result
            }.onFailure { error ->
                error.printStackTrace()
                _interestOptions.value = emptyList()
            }
        }
    }

    fun getPreInvestigationNewsLetters() {
        viewModelScope.launch {
            _preInvestigateNewsLettersUiState.value = UiState.Loading

            runCatching {
                getPreInvestigateNewsLettersUseCase(
                    GetPreInvestigateNewsLettersUseCase.GetPreInvestigateNewsLettersParams(
                        industry = selectedIndustry,
                        interests = _selectedInterests.value.mapNotNull { interest ->
                            InterestCategory.getInterestById(interest.id)
                        }
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