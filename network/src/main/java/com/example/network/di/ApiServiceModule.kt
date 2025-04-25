package com.example.network.di

import com.apollographql.apollo.ApolloClient
import com.example.domain.network.ApiService
import com.example.network.network.ApiServiceImpl
import org.koin.dsl.module

val apiServiceModule = module {
    single {
        ApolloClient.Builder()
            .serverUrl("https://graphql.anilist.co")
            .addHttpHeader("Accept","application/json")
            .build()

    }

    single<ApiService> {
        ApiServiceImpl(get())
    }
}