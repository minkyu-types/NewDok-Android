package com.and.presentation.screen.bookmark

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.and.domain.model.type.InterestCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(

) : ViewModel() {
    var selectedInterests by mutableStateOf(emptySet<InterestCategory>())
        private set

    fun toggleInterest(interest: InterestCategory) {
        val currentInterests = selectedInterests.toMutableSet()
        if (interest in currentInterests) {
            currentInterests.remove(interest)
        } else {
            currentInterests.add(interest)
        }
        selectedInterests = currentInterests
    }
}