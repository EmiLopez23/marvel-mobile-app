package com.example.marvel.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.marvel.R
import com.example.marvel.navigation.enums.Routes
import com.example.marvel.ui.components.ComicCard
import com.example.marvel.ui.components.MarvelCard
import com.example.marvel.viewModels.FavoritesViewModel

@Composable
fun Favorites(
    onNavigate: (String) -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel(),
) {
    val favoriteHeroes by viewModel.heroes.collectAsState(emptyList())
    val favoriteComics by viewModel.comics.collectAsState(emptyList())
    var selectedOption by remember { mutableStateOf("Heroes") }

    Column {
        Text(
            text = stringResource(id = R.string.favorites_title),
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        Selector(
            selectedOption = selectedOption,
            onOptionSelected = { option -> selectedOption = option }
        )
        if (selectedOption == "Heroes") {
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
                                hero.thumbnail,
                                hero.type
                            )
                        },
                        isFavorite = true
                    )
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(
                    vertical = 8.dp,
                )
            ) {
                items(favoriteComics.size) { index ->
                    val comic = favoriteComics[index]
                    ComicCard(
                        title = comic.name,
                        thumbnail = comic.thumbnail,
                        isFavorite = true,
                        onClick = { onNavigate("${Routes.ComicDetail.name}/${comic.id}") },
                        onFavoriteClick = {
                            viewModel.toggleFavorite(
                                comic.id,
                                comic.name,
                                comic.thumbnail,
                                comic.type
                            )
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun Selector(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val buttons = listOf("Heroes", "Comics")
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        buttons.forEach { buttonText ->
            OutlinedButton(
                onClick = { onOptionSelected(buttonText) },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = if (selectedOption == buttonText) Color.White else MaterialTheme.colorScheme.tertiary,
                    containerColor = if (selectedOption == buttonText) Color.Red else Color.Unspecified

                ),
                border = BorderStroke(width = 1.dp, color = Color.Red),
                modifier = Modifier.weight(1f)
            ) {
                Text(buttonText)
            }
        }

    }
}