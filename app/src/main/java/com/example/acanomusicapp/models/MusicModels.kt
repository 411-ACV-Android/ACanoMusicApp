package com.example.acanomusicapp.models

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: String,
    val title: String,
    val artist: String,
    val description: String = "",
    val image: String
)

sealed class NetworkResponse {
    object Loading : NetworkResponse()
    data class Success(val data: List<Album>) : NetworkResponse()
    data class SuccessDetail(val album: Album) : NetworkResponse()
    data class Error(val message: String) : NetworkResponse()
}