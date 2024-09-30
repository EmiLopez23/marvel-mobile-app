package com.example.marvel.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteCharacter::class], version = 1)
abstract class FavoriteCharactersDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoriteCharacterDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteCharactersDatabase? = null
        fun getDatabase(context: Context): FavoriteCharactersDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteCharactersDatabase::class.java,
                    "marvel_heroes_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}