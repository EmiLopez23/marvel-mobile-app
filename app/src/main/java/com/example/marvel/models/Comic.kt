package com.example.marvel.models

data class Comic(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: Thumbnail,
)