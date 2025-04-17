package com.example.auth.repository


import com.example.domain.model.AuthUser
import com.example.domain.model.Response
import com.example.domain.network.NetworkService
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val networkService: NetworkService,
) : AuthRepository {
    override val currentUser: AuthUser? = networkService.currentUser

    override suspend fun loginWithEmailAndPassword(email: String, password: String): Response {
        return networkService.loginWithEmailAndPassword(email, password)
    }

    override suspend fun registerWithEmailAndPassword(email: String, password: String): Response {
        return networkService.registerWithEmailAndPassword(email, password)
    }

    override suspend fun logout(): Response {
        return networkService.logout()
    }

    override fun getAuthState(): Flow<Boolean> {
        return networkService.getAuthState()
    }
}