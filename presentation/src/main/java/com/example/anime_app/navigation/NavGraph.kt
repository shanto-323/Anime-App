package com.example.anime_app.navigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.anime_app.ui.feature.auth.login.Login
import com.example.anime_app.ui.feature.auth.signup.SignUp
import com.example.anime_app.ui.feature.content.home.Home
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(
    viewModel: NavigationViewModel = koinViewModel(),
) {
    val navController = rememberNavController()
    val authState = viewModel.authState.collectAsState(initial = false)
    val isLoading = viewModel.loading.collectAsState()

    LaunchedEffect(authState.value, isLoading.value) {
        if (!isLoading.value) {
            navController.navigate(if (authState.value) Content else Auth) {
                popUpTo(LoadingScreen) {
                    inclusive = true
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = LoadingScreen,
    ) {
        composable<LoadingScreen> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Text(text = "Loading...")
            }
        }


        navigation<Auth>(startDestination = LoginScreen) {
            composable<LoginScreen> {
                Login(
                    navigateToSignUp = {
                        navController.navigate(SignUpScreen) {
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable<SignUpScreen> {
                SignUp(
                    navigateToLogin = {
                        navController.popBackStack()
                    },
                )
            }
        }

        navigation<Content>(startDestination = HomeScreen) {
            composable<HomeScreen> {
                Home()
            }
        }
    }
}
