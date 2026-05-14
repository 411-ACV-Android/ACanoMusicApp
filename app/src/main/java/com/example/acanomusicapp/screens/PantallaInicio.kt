package com.example.acanomusicapp.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.acanomusicapp.models.NetworkResponse
import com.example.acanomusicapp.services.RetrofitClient

@Composable
fun HomeScreen() {
    var uiState by remember { mutableStateOf<NetworkResponse>(NetworkResponse.Loading) }
    LaunchedEffect(Unit) {
        try {
            val albums = RetrofitClient.instance.getAlbums()
            uiState = NetworkResponse.Success(albums)
        } catch (e: Exception) {
            uiState = NetworkResponse.Error("Error: ${e.message}")
        }
    }

    when (val state = uiState) {
        is NetworkResponse.Loading -> {
            Text(text = "Cargando tu música, Abraham...")
        }
        is NetworkResponse.Success -> {
            Text(text = "¡Éxito! Se descargaron ${state.data.size} álbumes de internet.")
        }
        is NetworkResponse.Error -> {
            Text(text = state.message)
        }

        else -> {}
    }
}