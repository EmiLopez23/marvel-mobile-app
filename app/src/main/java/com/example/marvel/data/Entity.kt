package com.example.marvel.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey val id: Int,
    val name: String,
    val thumbnail: String,
    val type: FavoriteType
)

enum class FavoriteType {
    HERO,
    COMIC
}