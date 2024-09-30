package com.example.marvel.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteCharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(favoriteHero: FavoriteCharacter)

    @Query("SELECT * FROM favorite_characters")
    fun getFavoriteHeroes(): LiveData<List<FavoriteCharacter>>

    @Query("DELETE FROM favorite_characters WHERE id = :heroId")
    suspend fun deleteFromFavoritesById(heroId: Int)

    @Query("SELECT COUNT(*) > 0 FROM favorite_characters WHERE id = :heroId")
    suspend fun isFavorite(heroId: Int): Boolean
}