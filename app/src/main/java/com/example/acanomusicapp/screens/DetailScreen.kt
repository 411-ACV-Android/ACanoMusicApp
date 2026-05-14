package com.example.acanomusicapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.acanomusicapp.DetailRoute

@Composable
fun DetailScreen(detail: DetailRoute, navController: NavHostController) {
    val backgroundColor = Color(0xFFF0EFFF)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .statusBarsPadding()
        ) {
            AsyncImage(
                model = detail.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(RoundedCornerShape(40.dp))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.background(Color.Black.copy(alpha = 0.3f), CircleShape)
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
                }
                IconButton(
                    onClick = { },
                    modifier = Modifier.background(Color.Black.copy(alpha = 0.3f), CircleShape)
                ) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = null, tint = Color.White)
                }
            }

            Surface(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(12.dp)
                    .fillMaxWidth(),
                color = Color(0xFF2D2545).copy(alpha = 0.7f),
                shape = RoundedCornerShape(30.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(detail.title, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        Text(detail.artist, color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp)
                    }
                    IconButton(
                        onClick = { },
                        modifier = Modifier.size(45.dp).background(Color(0xFF8A5BFF), CircleShape)
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = { },
                        modifier = Modifier.size(45.dp).background(Color.White, CircleShape)
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color(0xFF2D2545))
                    }
                }
            }
        }

        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("About this album", fontWeight = FontWeight.Bold, color = Color(0xFF2D2545))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        detail.description.ifEmpty { "Un álbum sinfónico que mezcla elementos de música clásica con death metal melódico." },
                        fontSize = 14.sp, color = Color.Gray
                    )
                }
            }

            Surface(
                modifier = Modifier.padding(vertical = 16.dp),
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Artist: ${detail.artist}",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    color = Color(0xFF6B4DFF), fontWeight = FontWeight.Bold, fontSize = 12.sp
                )
            }

            repeat(2) { index ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(model = detail.image, contentDescription = null, modifier = Modifier.size(50.dp).clip(RoundedCornerShape(8.dp)))
                        Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
                            Text("${detail.title} • Track ${index + 1}", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text(detail.artist, color = Color.Gray, fontSize = 12.sp)
                        }
                        Icon(Icons.Default.MoreVert, contentDescription = null, tint = Color.LightGray)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}