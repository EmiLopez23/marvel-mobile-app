package com.example.marvel.models

data class Hero(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
    val comics: CollectionResponse,
    val series: CollectionResponse,
    val stories: CollectionResponse,
    val events: CollectionResponse,
    val urls: List<Url>
)

data class CollectionResponse(
    val available: Int,
    val collectionURI: String,
    val items: List<CollectionItem>,
)

data class CollectionItem(
    val resourceURI: String,
    val name: String
)

data class Url(
    val type: String,
    val url: String
)

data class Thumbnail(
    val path: String,
    val extension: String
)
