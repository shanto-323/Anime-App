package com.example.anime_app.di

import com.example.anime_app.navigation.NavigationViewModel
import com.example.anime_app.ui.feature.auth.login.LoginViewModel
import com.example.anime_app.ui.feature.auth.signup.SignUpViewModel
import com.example.anime_app.ui.feature.content.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignUpViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { NavigationViewModel(get()) }
}