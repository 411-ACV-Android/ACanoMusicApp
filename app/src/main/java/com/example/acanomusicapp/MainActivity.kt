package com.example.acanomusicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.acanomusicapp.screens.HomeScreen
import com.example.acanomusicapp.ui.theme.ACanoMusicAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ACanoMusicAppTheme {
                HomeScreen()
            }
        }
    }
}