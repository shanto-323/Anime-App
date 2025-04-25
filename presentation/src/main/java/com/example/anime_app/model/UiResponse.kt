package com.example.anime_app.model

sealed class UiResponse<out T> {
    data object Nothing : UiResponse<Nothing>()
    data object Loading : UiResponse<Nothing>()
    data class Success<out T>(val data: T) : UiResponse<T>()
    data class Error(val msg: String) : UiResponse<String>()
}