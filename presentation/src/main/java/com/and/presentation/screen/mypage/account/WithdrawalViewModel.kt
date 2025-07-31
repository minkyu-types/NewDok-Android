package com.and.presentation.screen.mypage.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.and.domain.usecase.user.DeleteUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WithdrawalViewModel @Inject constructor(
    private val deleteUserUseCase: DeleteUserUseCase
): ViewModel() {

    fun withdrawal() {
        viewModelScope.launch {
            val apiResult = deleteUserUseCase(Unit)
        }
    }
}