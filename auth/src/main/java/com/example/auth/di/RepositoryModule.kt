package com.example.auth.di

import com.example.auth.repository.AuthRepositoryImpl
import com.example.domain.repository.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }
}