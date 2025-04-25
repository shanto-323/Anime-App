package com.example.anime_app.ui.feature.content.anime

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.anime_app.model.UiResponse
import org.koin.androidx.compose.koinViewModel

@Composable
fun AnimeScreen(
    viewModel: AnimeViewModel = koinViewModel()
) {
    val response = viewModel.response.collectAsState().value
    val context = LocalContext.current
    val navController = rememberNavController()

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

        is UiResponse.Success -> {
            response.data.let {
                Log.d("TAG2", it.toString())
            }
        }

        else -> {}
    }
}