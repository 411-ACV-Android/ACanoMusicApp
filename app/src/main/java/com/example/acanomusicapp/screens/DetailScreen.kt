package com.example.acanomusicapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.acanomusicapp.DetailRoute
import com.example.acanomusicapp.components.MiniPlayer

@Composable
fun DetailScreen(detail: DetailRoute, navController: NavHostController) {
    val backgroundColor = Color(0xFFF3F2FF)

    Box(modifier = Modifier.fillMaxSize().background(backgroundColor)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 90.dp)
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth().padding(16.dp).statusBarsPadding()) {
                    AsyncImage(
                        model = detail.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().height(380.dp).clip(RoundedCornerShape(40.dp))
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = { navController.popBackStack() },
                            modifier = Modifier.background(Color.Black.copy(0.3f), CircleShape)) {
                            Icon(Icons.Default.ArrowBack, null, tint = Color.White)
                        }
                        IconButton(onClick = { },
                            modifier = Modifier.background(Color.Black.copy(0.3f), CircleShape)) {
                            Icon(Icons.Default.FavoriteBorder, null, tint = Color.White)
                        }
                    }

                    Surface(
                        modifier = Modifier.align(Alignment.BottomCenter).padding(12.dp).fillMaxWidth(),
                        color = Color(0xFF2D2545).copy(alpha = 0.8f),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(detail.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                                Text(detail.artist, color = Color.White.copy(0.7f), fontSize = 14.sp)
                            }
                            IconButton(onClick = {}, modifier = Modifier.size(40.dp).background(Color(0xFF8A5BFF), CircleShape)) {
                                Icon(Icons.Default.PlayArrow, null, tint = Color.White)
                            }
                            Spacer(Modifier.width(8.dp))
                            IconButton(onClick = {}, modifier = Modifier.size(40.dp).background(Color.White, CircleShape)) {
                                Icon(Icons.Default.PlayArrow, null, tint = Color(0xFF2D2545))
                            }
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text("About this album", fontWeight = FontWeight.Bold, color = Color(0xFF2D2545))
                        Spacer(Modifier.height(8.dp))
                        Text(detail.description.ifEmpty { "Un álbum icónico que define el género con su mezcla de sonidos clásicos y modernos." },
                            color = Color.Gray, fontSize = 14.sp)
                    }
                }
            }

            item {
                Surface(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Artist: ${detail.artist}", modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        color = Color(0xFF6B4DFF), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }

            items(5) { index ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(model = detail.image, contentDescription = null,
                            modifier = Modifier.size(50.dp).clip(RoundedCornerShape(8.dp)))
                        Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
                            Text("${detail.title} • Track ${index + 1}", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text(detail.artist, color = Color.Gray, fontSize = 12.sp)
                        }
                        Icon(Icons.Default.MoreVert, null, tint = Color.LightGray)
                    }
                }
            }
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            MiniPlayer()
        }
    }
}