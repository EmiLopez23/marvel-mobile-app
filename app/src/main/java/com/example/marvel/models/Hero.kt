package com.example.marvel.models

data class Hero(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val isFavorite: Boolean = false,
    val comics: CollectionResponse,
    val series: CollectionResponse,
    val stories: CollectionResponse,
    val events: CollectionResponse,
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

data class Thumbnail(
    val path: String,
    val extension: String
)

val initialHero = Hero(
    id = 0,
    name = "Unknown Hero",
    description = "No description available",
    thumbnail = Thumbnail(
        path = "",
        extension = "jpg"
    ),
    comics = CollectionResponse(
        available = 0,
        collectionURI = "",
        items = emptyList()
    ),
    series = CollectionResponse(
        available = 0,
        collectionURI = "",
        items = emptyList()
    ),
    stories = CollectionResponse(
        available = 0,
        collectionURI = "",
        items = emptyList()
    ),
    events = CollectionResponse(
        available = 0,
        collectionURI = "",
        items = emptyList()
    ),
)
