package com.and.presentation.screen.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {
    private val _loginFailed = mutableStateOf(false)
    val loginFailed: State<Boolean> = _loginFailed


}