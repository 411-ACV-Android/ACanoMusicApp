package com.example.acanomusicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header() {
    val purpleScrim = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF9B82FF),
            Color(0xFF6B4DFF),
            Color(0xFF121212)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(purpleScrim)
            .statusBarsPadding()
            .padding(horizontal = 24.dp, vertical = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = null, tint = Color.White)
            Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Good Morning!", color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp)
        Text(text = "Abraham", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
    }
}