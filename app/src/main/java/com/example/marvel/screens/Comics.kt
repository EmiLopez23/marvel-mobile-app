package com.example.marvel.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.marvel.ui.components.ComicCard
import com.example.marvel.ui.components.Loader
import com.example.marvel.ui.components.Retry
import com.example.marvel.utils.modifier.helpers.formatImageUrl
import com.example.marvel.viewModels.ComicsViewModel

@Composable
fun Comics(
    viewModel: ComicsViewModel = hiltViewModel(),
) {
    val comics by viewModel.comics.collectAsState()
    val loading by viewModel.loadingComics.collectAsState()
    val showRetry by viewModel.showRetry.collectAsState()

    when {
        loading -> Loader()
        showRetry -> Retry(onClick = { viewModel.retryLoadingHeroes() })
        else -> LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(
                vertical = 8.dp,
            )
        ) {
            items(comics.size) { index ->
                val comic = comics[index]
                ComicCard(
                    title = comic.title,
                    thumbnail = formatImageUrl(comic.thumbnail.path, comic.thumbnail.extension)
                )
            }
        }

    }

}