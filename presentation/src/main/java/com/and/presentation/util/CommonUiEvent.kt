package com.and.presentation.util

sealed class CommonUiEvent {
    data class ShowToast(val message: String): CommonUiEvent()
}