package com.example.acanomusicapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acanomusicapp.components.AlbumCard
import com.example.acanomusicapp.components.Header
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

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Header()
        }

        item {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Albums",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                when (val state = uiState) {
                    is NetworkResponse.Loading -> {
                        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(color = Color(0xFF6B4DFF))
                        }
                    }
                    is NetworkResponse.Success -> {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(state.data) { album ->
                                AlbumCard(album = album) {
                                    // Navegación pendiente
                                }
                            }
                        }
                    }
                    is NetworkResponse.Error -> {
                        Text(text = state.message, color = Color.Red)
                    }
                    else -> {}
                }
            }
        }
    }
}