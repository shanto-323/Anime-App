package com.example.anime_app.ui.feature.content.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime_app.model.UiResponse
import com.example.domain.model.Response
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _response = MutableStateFlow<UiResponse>(UiResponse.Nothing)
    val response = _response.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.LogOut -> signOut()
        }
    }

    private fun signOut() {
        _response.value = UiResponse.Loading
        viewModelScope.launch {
            val auth = authUseCase.logout()
            when (auth) {
                is Response.Error -> {
                    _response.value = UiResponse.Error(auth.msg)
                    delay(1000)
                    _response.value = UiResponse.Nothing
                }

                Response.Success -> _response.value = UiResponse.Success
            }
        }
    }
}


sealed class HomeEvent {
    data object LogOut : HomeEvent()
}