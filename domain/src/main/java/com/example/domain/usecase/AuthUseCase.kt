package com.example.domain.usecase

import com.example.domain.model.Response
import com.example.domain.repository.AuthRepository

class AuthUseCase(
    private val repository: AuthRepository
) {
    suspend fun loginWithEmailAndPassword(email: String, password: String): Response<String> {
        return repository.loginWithEmailAndPassword(email, password)
    }

    suspend fun signUpWithEmailAndPassword(email: String, password: String): Response<String> {
        return repository.registerWithEmailAndPassword(email, password)
    }

    suspend fun logout(): Response<String> {
        return repository.logout()
    }

    val currentUser = repository.currentUser
    fun getAuthState() = repository.getAuthState()
}