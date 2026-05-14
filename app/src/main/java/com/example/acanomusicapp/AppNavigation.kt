package com.example.acanomusicapp

import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
data class DetailRoute(
    val id: String,
    val title: String,
    val artist: String,
    val image: String,
    val description: String
)