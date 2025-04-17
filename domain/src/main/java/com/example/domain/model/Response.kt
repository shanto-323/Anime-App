package com.example.domain.model

sealed class Response {
    data object Success : Response()
    data class Error(val msg: String) : Response()
}