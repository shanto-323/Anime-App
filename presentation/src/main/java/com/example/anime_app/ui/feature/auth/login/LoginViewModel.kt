package com.example.anime_app.ui.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime_app.model.UiResponse
import com.example.domain.model.Response
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _response = MutableStateFlow<UiResponse>(UiResponse.Nothing)
    val response = _response.asStateFlow()

    fun logInEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.NameChanged ->
                _state.value = _state.value.copy(name = event.name)

            is LoginEvent.PasswordChanged ->
                _state.value = _state.value.copy(password = event.password)

            LoginEvent.Submit -> {
                login()
            }
        }
    }

    private fun login() {
        _response.value = UiResponse.Loading

        if (state.value.name == "" || state.value.password.length < 3) {
            _response.value = UiResponse.Error("Invalid Credentials")
            return
        }

        viewModelScope.launch {
            val auth =
                authUseCase.loginWithEmailAndPassword(_state.value.name, _state.value.password)
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


data class LoginState(
    val name: String = "",
    val password: String = "",
)

sealed class LoginEvent {
    data object Submit : LoginEvent()
    data class NameChanged(val name: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
}