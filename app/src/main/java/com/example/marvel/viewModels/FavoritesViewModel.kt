package com.example.marvel.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.marvel.data.FavoriteCharacter
import com.example.marvel.data.FavoriteCharactersDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    @ApplicationContext val context: Context,
) : ViewModel() {

    private val _database = FavoriteCharactersDatabase.getDatabase(context)
    private val favoritesDao = _database.favoritesDao()


    val heroes = favoritesDao.getFavoriteHeroes().asFlow()

    fun toggleFavorite(id: Int, name: String, thumbnail: String) {
        viewModelScope.launch {
            if (favoritesDao.isFavorite(id)) {
                deleteFavorites(id)
            } else {
                insertFavorite(FavoriteCharacter(id, name, thumbnail))
            }
        }
    }

    fun deleteFavorites(heroId: Int) {
        viewModelScope.launch {
            favoritesDao.deleteFromFavoritesById(heroId)
        }
    }

    fun insertFavorite(hero: FavoriteCharacter) {
        viewModelScope.launch {
            favoritesDao.addToFavorites(hero)
        }
    }
}