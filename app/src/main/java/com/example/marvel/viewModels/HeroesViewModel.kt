package com.example.marvel.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.marvel.api.ApiServiceImpl
import com.example.marvel.data.FavoriteCharactersDatabase
import com.example.marvel.models.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val service: ApiServiceImpl,
) : ViewModel() {


    private val _loading = MutableStateFlow(true)
    val loadingHeroes: StateFlow<Boolean> = _loading.asStateFlow()

    private val _heroes = MutableStateFlow(listOf<Hero>())
    val heroes: StateFlow<List<Hero>> = _heroes.asStateFlow()

    private val _showRetry = MutableStateFlow(false)
    val showRetry: StateFlow<Boolean> = _showRetry.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _database = FavoriteCharactersDatabase.getDatabase(context)
    private val favorites = _database.favoritesDao().getFavoriteHeroes().asFlow()

    private val favoriteHeroes = MutableStateFlow<Set<Int>>(emptySet())

    init {
        loadHeroes()
        observeFavorites()
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            favorites.collect { favorites ->
                favoriteHeroes.value = if (favorites.isEmpty()) {
                    emptySet()
                } else {
                    favorites.map { it.id }.toSet()
                }
                _heroes.value = _heroes.value.map { hero ->
                    hero.copy(isFavorite = favoriteHeroes.value.contains(hero.id))
                }
            }
        }
    }

    private fun loadHeroes() {
        service.getHeroes(
            context = context,
            onSuccess = { response ->
                viewModelScope.launch {
                    _heroes.emit(response.map { hero ->
                        val isFavorite = favoriteHeroes.value.contains(hero.id)
                        hero.copy(isFavorite = isFavorite)
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
        loadHeroes()
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}