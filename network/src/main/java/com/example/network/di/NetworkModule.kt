package com.example.network.di

import org.koin.dsl.module

val networkModule = module {
    includes(
        apiRepositoryModule,
        apiServiceModule
    )
}