package com.example.anime_app.model

sealed class UiResponse {
    data object Nothing : UiResponse()
    data object Loading : UiResponse()
    data object Success : UiResponse()
    data class Error(val msg: String) : UiResponse()
}