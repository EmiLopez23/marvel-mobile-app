package com.example.marvel.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.marvel.data.Favorite
import com.example.marvel.data.FavoriteType
import com.example.marvel.data.FavoritesDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    @ApplicationContext val context: Context,
) : ViewModel() {

    private val _database = FavoritesDatabase.getDatabase(context)
    private val favoritesDao = _database.favoritesDao()


    val heroes = favoritesDao.getFavoriteHeroes().asFlow()
    val comics = favoritesDao.getFavoriteComics().asFlow()

    fun toggleFavorite(id: Int, name: String, thumbnail: String, type: FavoriteType) {
        viewModelScope.launch {
            if (favoritesDao.isFavorite(id)) {
                deleteFavorites(id)
            } else {
                insertFavorite(Favorite(id, name, thumbnail, type))
            }
        }
    }

    fun deleteFavorites(id: Int) {
        viewModelScope.launch {
            favoritesDao.deleteFromFavoritesById(id)
        }
    }

    fun insertFavorite(favorite: Favorite) {
        viewModelScope.launch {
            favoritesDao.addToFavorites(favorite)
        }
    }
}