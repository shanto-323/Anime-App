package com.example.network.di

import com.example.domain.repository.ApiRepository
import com.example.network.reposotory.ApiRepositoryImpl
import org.koin.dsl.module

val apiRepositoryModule = module {
    single<ApiRepository> {
        ApiRepositoryImpl(get())
    }
}