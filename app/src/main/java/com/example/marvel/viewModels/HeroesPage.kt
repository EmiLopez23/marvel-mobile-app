package com.example.marvel.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.marvel.api.ApiServiceImpl
import com.example.marvel.models.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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


    init {
        loadHeroes()
    }

    private fun loadHeroes() {
        service.getHeroes(
            context = context,
            onSuccess = {
                _heroes.value = it
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
        loadHeroes()
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}