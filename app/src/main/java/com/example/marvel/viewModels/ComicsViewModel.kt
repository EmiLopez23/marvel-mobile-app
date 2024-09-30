package com.example.marvel.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.marvel.api.ApiServiceImpl
import com.example.marvel.models.Comic
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val service: ApiServiceImpl,
) : ViewModel() {

    private val _loading = MutableStateFlow(true)
    val loadingComics: StateFlow<Boolean> = _loading.asStateFlow()

    private val _comics: MutableStateFlow<List<Comic>> = MutableStateFlow(emptyList())
    val comics: StateFlow<List<Comic>> = _comics.asStateFlow()

    private val _showRetry = MutableStateFlow(false)
    val showRetry: StateFlow<Boolean> = _showRetry.asStateFlow()

    init {
        loadComics()
    }

    fun loadComics() {
        service.getComics(
            context = context,
            onSuccess = {
                _comics.value = it
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
}