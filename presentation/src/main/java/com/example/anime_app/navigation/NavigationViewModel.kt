package com.example.anime_app.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NavigationViewModel(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    val authState get() = authUseCase.getAuthState()
    val loading = MutableStateFlow(true)

    init {
        viewModelScope.launch {
            authUseCase.getAuthState().collect {
                loading.value = false
            }
        }
    }


}