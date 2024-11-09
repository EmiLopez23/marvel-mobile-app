package com.example.marvel.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(favorite: Favorite)

    @Query("SELECT * FROM favorites WHERE type = :type")
    fun getFavoriteHeroes(type: FavoriteType = FavoriteType.HERO): LiveData<List<Favorite>>


    @Query("SELECT * FROM favorites WHERE type = :type")
    fun getFavoriteComics(type: FavoriteType = FavoriteType.COMIC): LiveData<List<Favorite>>

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun deleteFromFavoritesById(id: Int)

    @Query("SELECT COUNT(*) > 0 FROM favorites WHERE id = :id")
    suspend fun isFavorite(id: Int): Boolean
}