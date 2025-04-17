package com.example.domain.di

import com.example.domain.usecase.AuthUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AuthUseCase(get()) }
}