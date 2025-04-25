package com.example.anime_app

import android.app.Application
import com.example.anime_app.di.presentationModule
import com.example.auth.di.authModule
import com.example.domain.di.domainModule
import com.example.network.di.networkModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class AnimeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AnimeApplication)
            modules(
                listOf(
                    authModule,
                    domainModule,
                    presentationModule,
                    networkModule
                )
            )
        }
    }
}