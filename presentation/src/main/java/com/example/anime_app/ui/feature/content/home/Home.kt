package com.example.anime_app.ui.feature.content.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Animation
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.anime_app.model.UiResponse
import com.example.anime_app.navigation.AnimeScreen
import com.example.anime_app.navigation.Content
import com.example.anime_app.navigation.FavoriteScreen
import com.example.anime_app.navigation.ProfileScreen
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun Home(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val response = viewModel.response.collectAsState().value
    val context = LocalContext.current
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = AnimeScreen,
            ) {
                composable<AnimeScreen> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Green),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Anime Screen")
                    }
                }
                composable<FavoriteScreen> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Green),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Favorite Screen")
                    }
                }
                composable<ProfileScreen> {
                    ProfileScreen(
                        response = response,
                        context = context,
                        onEvent = {
                            viewModel.onEvent(it)
                        }
                    )
                }
            }

        }
    }


}

@Composable
fun ProfileScreen(
    response: UiResponse,
    context: Context,
    onEvent: (HomeEvent) -> Unit,
) {
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

        Button(
            onClick = {
                onEvent(HomeEvent.LogOut)
            }
        ) {
            Text(text = "Sign Up")
        }
    }
}


@Composable
fun BottomNavBar(
    navController: NavController
) {
    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        val items = listOf(
            BottomNavBarItems.Anime,
            BottomNavBarItems.Favorite,
            BottomNavBarItems.Profile
        )

        items.forEach { item ->
            val isSelected = currentRoute?.substringAfter('?') == item.route
            NavigationBarItem(
                selected = isSelected,
                onClick = { navController.navigate(item.route) },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = "")
                },
                label = { Text(text = item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Green,
                    unselectedIconColor = Color.Black,
                    selectedTextColor = Color.Green,
                    unselectedTextColor = Color.Black,
                )
            )
        }
    }
}


sealed class BottomNavBarItems(
    val route: Any,
    val title: String,
    val icon: ImageVector
) {
    data object Anime : BottomNavBarItems(AnimeScreen, "anime", Icons.Outlined.Animation)
    data object Favorite : BottomNavBarItems(FavoriteScreen, "favorite", Icons.Outlined.Bookmark)
    data object Profile : BottomNavBarItems(ProfileScreen, "profile", Icons.Outlined.Person)
}