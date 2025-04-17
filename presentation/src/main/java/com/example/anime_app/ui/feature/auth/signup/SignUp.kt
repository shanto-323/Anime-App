package com.example.anime_app.ui.feature.auth.signup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.anime_app.model.UiResponse
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUp(
    viewModel: SignUpViewModel = koinViewModel(),
    navigateToLogin: () -> Unit
) {
    val state = viewModel.state.collectAsState().value
    val response = viewModel.response.collectAsState().value
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (response) {
            UiResponse.Loading -> {
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

            is UiResponse.Error -> {
                Toast.makeText(context, response.msg, Toast.LENGTH_SHORT).show()
                Log.d("TAG2", response.msg)
            }

            UiResponse.Success -> {
                Toast.makeText(context, "Signed Up Successfully", Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }

        OutlinedTextField(
            value = state.name,
            onValueChange = { viewModel.onEvent(SignUpEvent.NameChanged(it)) }
        )
        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onEvent(SignUpEvent.PasswordChanged(it)) }
        )
        Button(
            onClick = { viewModel.onEvent(SignUpEvent.Submit) }
        ) {
            Text(text = "Sign Up")
        }
        Button(
            onClick = {
                navigateToLogin()
            }
        ) {
            Text(text = "Log In")
        }
    }

}