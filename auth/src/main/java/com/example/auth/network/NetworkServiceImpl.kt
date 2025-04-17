package com.example.auth.network

import com.example.domain.model.AuthUser
import com.example.domain.model.Response
import com.example.domain.network.NetworkService
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionStatus

import kotlinx.coroutines.flow.callbackFlow

class NetworkServiceImpl(
    private val client: SupabaseClient,
) : NetworkService {
    override val currentUser: AuthUser? = null

    override fun getAuthState() = callbackFlow {
        client.auth.sessionStatus.collect {
            when (it) {
                is SessionStatus.Authenticated -> trySend(true)
                is SessionStatus.NotAuthenticated -> trySend(false)
                else -> Unit
            }
        }
    }

    override suspend fun loginWithEmailAndPassword(email: String, password: String): Response {
        return try {
            client.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            Response.Success
        } catch (e: Exception) {
            Response.Error(e.message.toString())
        }
    }

    override suspend fun registerWithEmailAndPassword(email: String, password: String): Response {
        return try {
            client.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            Response.Success
        } catch (e: Exception) {
            Response.Error(e.message.toString())
        }
    }

    override suspend fun logout(): Response {
        return try {
            client.auth.signOut()
            Response.Success
        } catch (e: Exception) {
            Response.Error(e.message.toString())
        }
    }
}