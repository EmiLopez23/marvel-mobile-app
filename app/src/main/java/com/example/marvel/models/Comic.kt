package com.example.marvel.models

data class Comic(
    val id: Int,
    val title: String,
    val description: String? = null,
    val thumbnail: Thumbnail,
    val isFavorite: Boolean
)

val initialComic = Comic(
    id = 1,
    title = "",
    description = "",
    thumbnail = Thumbnail(
        path = "",
        extension = "jpg"
    ),
    isFavorite = false
)