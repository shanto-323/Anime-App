package com.example.auth.di

import org.koin.dsl.module

val authModule = module {
    includes(netWorkModule, repositoryModule)
}
