package com.example.marvel.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.marvel.R
import com.example.marvel.navigation.enums.Routes
import com.example.marvel.ui.components.MarvelCard
import com.example.marvel.viewModels.FavoritesViewModel

@Composable
fun Favorites(
    onNavigate: (String) -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel(),
) {
    val favoriteHeroes by viewModel.heroes.collectAsState(emptyList())

    Column {
        Text(
            text = stringResource(id = R.string.favorites_title),
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(
                vertical = 8.dp,
            )
        ) {
            items(favoriteHeroes.size) { index ->
                val hero = favoriteHeroes[index]
                MarvelCard(
                    thumbnail = hero.thumbnail,
                    name = hero.name,
                    onClick = { onNavigate("${Routes.HeroDetails.name}/${hero.id}") },
                    onFavoriteClick = {
                        viewModel.toggleFavorite(
                            hero.id,
                            hero.name,
                            hero.thumbnail
                        )
                    },
                    isFavorite = true
                )
            }
        }
    }

}