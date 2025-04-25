package com.example.domain.network

import com.example.domain.model.AuthUser
import com.example.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface NetworkService {
    suspend fun loginWithEmailAndPassword(email: String, password: String): Response<String>
    suspend fun registerWithEmailAndPassword(email: String, password: String): Response<String>
    suspend fun logout(): Response<String>


    val currentUser: AuthUser?
    fun getAuthState(): Flow<Boolean>
}