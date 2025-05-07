package com.and.presentation.screen.preinvestigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.model.type.IndustryCategory
import com.and.domain.model.type.InterestCategory
import com.and.domain.usecase.user.UpdateUserIndustryUseCase
import com.and.domain.usecase.user.UpdateUserInterestsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class InvestigationViewModel @Inject constructor(
    private val updateInterestsUseCase: UpdateUserInterestsUseCase,
    private val updateIndustryUseCase: UpdateUserIndustryUseCase
): ViewModel() {

    fun updateInterests(
        interests: List<InterestCategory>
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
        viewModelScope.launch(Dispatchers.IO) {
            updateIndustryUseCase(
                UpdateUserIndustryUseCase.UpdateUserIndustryParams(
                    industry.id
                )
            )
        }
    }
}