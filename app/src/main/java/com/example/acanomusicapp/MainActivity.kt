package com.example.acanomusicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.acanomusicapp.screens.DetailScreen
import com.example.acanomusicapp.screens.HomeScreen
import com.example.acanomusicapp.ui.theme.ACanoMusicAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ACanoMusicAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = HomeRoute
                ) {
                    composable<HomeRoute> {
                        HomeScreen(navController)
                    }

                    composable<DetailRoute> { backStackEntry ->
                        val detail: DetailRoute = backStackEntry.toRoute()
                        DetailScreen(detail, navController)
                    }
                }
            }
        }
    }
}