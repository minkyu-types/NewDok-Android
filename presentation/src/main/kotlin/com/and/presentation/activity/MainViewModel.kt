package com.and.presentation.activity

import androidx.lifecycle.ViewModel
import com.and.domain.usecase.user.GetUserAccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserAccessTokenUseCase: GetUserAccessTokenUseCase
) : ViewModel() {

    fun getAccessToken() = getUserAccessTokenUseCase(Unit)
        .flowOn(Dispatchers.IO)
}