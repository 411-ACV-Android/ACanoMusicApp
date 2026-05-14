package com.example.acanomusicapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acanomusicapp.components.*
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
            uiState = NetworkResponse.Error("Error de conexión")
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            item { Header() }

            item {
                Text("Albums", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp))

                if (uiState is NetworkResponse.Success) {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items((uiState as NetworkResponse.Success).data) { album ->
                            AlbumCard(album = album) { /* Click para navegación */ }
                        }
                    }
                }
            }

            item {
                Text("Recently Played", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp))
            }

            if (uiState is NetworkResponse.Success) {
                items((uiState as NetworkResponse.Success).data) { album ->
                    RecentlyPlayedCard(album = album) { /* Click para navegación */ }
                }
            }

            item { Spacer(modifier = Modifier.height(85.dp)) }
        }

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            MiniPlayer()
        }
    }
}