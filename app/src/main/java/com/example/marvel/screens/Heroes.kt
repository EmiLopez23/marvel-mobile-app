package com.example.marvel.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.marvel.ui.components.CustomSearchBar
import com.example.marvel.ui.components.MarvelCard

@Composable
fun Heroes() {
    var query by remember { mutableStateOf("") }

    val marvelCards = listOf(
        "Wolverine" to "https://example.com/wolverine.jpg",
        "Spider-Man" to "https://example.com/spiderman.jpg",
        "Iron Man" to "https://example.com/ironman.jpg",
        "Thor" to "https://example.com/thor.jpg",
        "Captain America" to "https://example.com/captainamerica.jpg",
        "Hulk" to "https://example.com/hulk.jpg",
        "Black Widow" to "https://example.com/blackwidow.jpg",
        "Black Panther" to "https://example.com/blackpanther.jpg",
        "Doctor Strange" to "https://example.com/doctorstrange.jpg",
        "Scarlet Witch" to "https://example.com/scarletwitch.jpg"
    )

    Column {
        CustomSearchBar(
            query,
            onQueryChange = { query = it },
            placeholder = "Search for heroes",
            onSearch = { /* Handle search */ }
        )
        HeroesList(marvelCards.filter { it.first.contains(query, true) })
    }
}

@Composable
fun HeroesList(heroes: List<Pair<String, String>>) {

    val itemsPerRow = 2 // Define how many items you want per row

    // Group the cards into rows
    val rows = heroes.chunked(itemsPerRow)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(rows) { rowItems ->
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(rowItems) { card ->
                    MarvelCard(title = card.first, url = card.second)
                }
            }
        }
    }
}