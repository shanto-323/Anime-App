package com.example.anime_app.ui.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime_app.model.UiResponse
import com.example.domain.model.Response
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    private val _response = MutableStateFlow<UiResponse>(UiResponse.Nothing)
    val response = _response.asStateFlow()

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.NameChanged ->
                _state.value = _state.value.copy(name = event.name)

            is SignUpEvent.PasswordChanged ->
                _state.value = _state.value.copy(password = event.password)

            SignUpEvent.Submit -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        _response.value = UiResponse.Loading

        if (state.value.name == "" || state.value.password.length < 3) {
            _response.value = UiResponse.Error("Invalid Credentials")
            return
        }

        viewModelScope.launch {
            val auth =
                authUseCase.signUpWithEmailAndPassword(_state.value.name, _state.value.password)
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

data class SignUpState(
    val name: String = "",
    val password: String = "",
)

sealed class SignUpEvent {
    data object Submit : SignUpEvent()
    data class NameChanged(val name: String) : SignUpEvent()
    data class PasswordChanged(val password: String) : SignUpEvent()
}