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
import com.example.marvel.models.Hero
import com.example.marvel.navigation.enums.Routes
import com.example.marvel.ui.components.CustomSearchBar
import com.example.marvel.ui.components.Loader
import com.example.marvel.ui.components.MarvelCard
import com.example.marvel.ui.components.Retry
import com.example.marvel.viewModels.HeroViewModel

@Composable
fun Heroes(
    onNavigate: (String) -> Unit,
    viewModel: HeroViewModel = hiltViewModel(),
) {
    val heroes by viewModel.heroes.collectAsState()
    val loading by viewModel.loadingHeroes.collectAsState()
    val showRetry by viewModel.showRetry.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    when {
        loading -> Loader()
        showRetry -> Retry(onClick = { viewModel.retryLoadingHeroes() })
        else -> Column {
            CustomSearchBar(
                searchQuery,
                onQueryChange = { viewModel.onSearchQueryChanged(it) },
                placeholder = "Search for heroes",
                onSearch = { /* Handle search */ }
            )
            HeroesList(
                heroes.filter { it.name.contains(searchQuery, true) },
                onNavigateToHeroDetails = { onNavigate("${Routes.HeroDetails.name}/$it") }
            )
        }
    }


}

@Composable
fun HeroesList(heroes: List<Hero>, onNavigateToHeroDetails: (Int) -> Unit) {

    if (heroes.isEmpty()) {
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
        items(heroes.size) { index ->
            val hero = heroes[index]
            MarvelCard(
                hero,
                onClick = { onNavigateToHeroDetails(hero.id) }
            )
        }
    }
}