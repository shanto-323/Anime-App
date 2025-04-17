package com.example.anime_app.di

import org.koin.dsl.module

val presentationModule = module {
    includes(viewModelModule)
}