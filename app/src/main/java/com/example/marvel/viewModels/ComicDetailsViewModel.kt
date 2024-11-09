package com.example.marvel.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.marvel.api.ApiServiceImpl
import com.example.marvel.models.Comic
import com.example.marvel.models.initialComic
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class ComicDetailViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val service: ApiServiceImpl,
) : ViewModel() {

    private val _loading = MutableStateFlow(true)
    val loadingHero: StateFlow<Boolean> = _loading.asStateFlow()

    //In case user needs to retry
    private val _comicId = MutableStateFlow("1")

    private val _comic = MutableStateFlow(initialComic)
    val comic: StateFlow<Comic> = _comic.asStateFlow()

    private val _showRetry = MutableStateFlow(false)
    val showRetry: StateFlow<Boolean> = _showRetry.asStateFlow()

    fun loadComicDetails(comicId: String) {
        _comicId.value = comicId
        service.getComicById(
            comicId = comicId,
            context = context,
            onSuccess = {
                _comic.value = it
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
        loadComicDetails(_comicId.value)
    }
}