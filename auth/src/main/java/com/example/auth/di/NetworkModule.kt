package com.example.auth.di

import android.util.Log
import com.example.auth.network.NetworkServiceImpl
import com.example.domain.network.NetworkService
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import org.koin.dsl.module

val netWorkModule = module {
    single {
        val SUPABASE_URL = "https://fdpbguxtruhuhorrtnaa.supabase.co"
        val SUPABASE_KEY =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZkcGJndXh0cnVodWhvcnJ0bmFhIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDQ2MzQ3MjYsImV4cCI6MjA2MDIxMDcyNn0.C4cTweKwMNIMbFSh3Vv7Vf7R7Bpm2tsVXV2ZyHcwoVk"
        createSupabaseClient(
            supabaseUrl = SUPABASE_URL,
            supabaseKey = SUPABASE_KEY
        ) {
            install(Auth)
        }
    }

    single<NetworkService> {
        NetworkServiceImpl(get())
    }
}