package com.example.marvel.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.marvel.R
import com.example.marvel.models.Comic
import com.example.marvel.models.Hero
import com.example.marvel.ui.components.ComicCard
import com.example.marvel.ui.components.CustomSearchBar
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
    val searchQuery by viewModel.searchQuery.collectAsState()


    when {
        loading -> Loader()
        showRetry -> Retry(onClick = { viewModel.retryLoadingHeroes() })
        else -> Column {
            CustomSearchBar(
                searchQuery,
                onQueryChange = { viewModel.onSearchQueryChanged(it) },
                placeholder = stringResource(id = R.string.comic_search_bar_placeholder),
                onSearch = { /* Handle search */ }
            )
            ComicList(
                comics.filter { it.title.contains(searchQuery, true) },
                onNavigateToComicDetails = { },
                onFavoriteClick = {}
            )
        }


    }

}


@Composable
fun ComicList(
    comics: List<Comic>,
    onNavigateToComicDetails: (Int) -> Unit,
    onFavoriteClick: (Hero) -> Unit
) {

    if (comics.isEmpty()) {
        return Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.no_content_on_query_message))
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
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
                thumbnail = formatImageUrl(comic.thumbnail.path, comic.thumbnail.extension),
                isFavorite = true,
                onFavoriteClick = {}
            )
        }
    }
}