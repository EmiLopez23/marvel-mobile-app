package com.example.marvel.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.marvel.api.ApiServiceImpl
import com.example.marvel.models.Hero
import com.example.marvel.models.initialHero
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val service: ApiServiceImpl,
) : ViewModel() {

    private val _loading = MutableStateFlow(true)
    val loadingHero: StateFlow<Boolean> = _loading.asStateFlow()

    //In case user needs to retry
    private val _heroId = MutableStateFlow("1")

    private val _hero = MutableStateFlow(initialHero)
    val hero: StateFlow<Hero> = _hero.asStateFlow()

    private val _showRetry = MutableStateFlow(false)
    val showRetry: StateFlow<Boolean> = _showRetry.asStateFlow()

    fun loadHeroDetails(heroId: String) {
        _heroId.value = heroId
        service.getHeroById(
            heroId = heroId,
            context = context,
            onSuccess = {
                _hero.value = it
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
        loadHeroDetails(_heroId.value)
    }
}