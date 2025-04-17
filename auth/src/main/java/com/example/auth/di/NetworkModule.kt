package com.example.auth.di

import android.util.Log
import com.example.auth.network.NetworkServiceImpl
import com.example.domain.network.NetworkService
import com.example.auth.BuildConfig

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import org.koin.dsl.module

val netWorkModule = module {
    single {
        val SUPABASE_URL = BuildConfig.SUPABASE_URL
        val SUPABASE_KEY = BuildConfig.SUPABASE_API_KEY
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