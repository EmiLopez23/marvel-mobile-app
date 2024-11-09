package com.example.marvel.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.marvel.api.ApiServiceImpl
import com.example.marvel.data.FavoritesDatabase
import com.example.marvel.models.Comic
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val service: ApiServiceImpl,
) : ViewModel() {

    private val _loading = MutableStateFlow(true)
    val loadingComics: StateFlow<Boolean> = _loading.asStateFlow()

    private val _comics = MutableStateFlow(listOf<Comic>())
    val comics: StateFlow<List<Comic>> = _comics.asStateFlow()

    private val _showRetry = MutableStateFlow(false)
    val showRetry: StateFlow<Boolean> = _showRetry.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _database = FavoritesDatabase.getDatabase(context)
    private val favorites = _database.favoritesDao().getFavoriteComics().asFlow()

    private val favoriteComics = MutableStateFlow<Set<Int>>(emptySet())

    init {
        loadComics()
        observeFavorites()
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            favorites.collect { favorites ->
                favoriteComics.value = if (favorites.isEmpty()) {
                    emptySet()
                } else {
                    favorites.map { it.id }.toSet()
                }
                _comics.value = _comics.value.map { comic ->
                    comic.copy(
                        isFavorite = favoriteComics.value.contains(comic.id),
                        description = comic.description ?: "No description available"
                    )
                }
            }
        }
    }

    private fun loadComics() {
        service.getComics(
            context = context,
            onSuccess = { response ->
                viewModelScope.launch {
                    _comics.emit(response.map { comic ->
                        val isFavorite = favoriteComics.value.contains(comic.id)
                        comic.copy(
                            isFavorite = isFavorite,
                            description = comic.description ?: "No description available"
                        )
                    })
                }
                _showRetry.value = false
            },
            onFail = {
                _showRetry.value = true
            },
            loadingFinished = {
                _loading.value = false
            }
        )
    }

    fun retryLoadingHeroes() {
        _loading.value = true
        loadComics()
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}