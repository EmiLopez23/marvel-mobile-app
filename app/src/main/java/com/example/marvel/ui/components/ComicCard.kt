package com.example.marvel.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.marvel.R
import com.example.marvel.ui.theme.MarvelRed

@Composable
fun ComicCard(
    title: String,
    thumbnail: String,
    onFavoriteClick: () -> Unit,
    isFavorite: Boolean = false
) {
    val image = if (thumbnail.contains("image_not_available")) {
        R.drawable.placeholder
    } else {
        thumbnail
    }

    Box {
        Column {
            AsyncImage(
                model = image,
                contentDescription = "$title thumbnail",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
            Text(
                text = title,
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )
        }
        Box(
            Modifier
                .align(Alignment.TopEnd)
                .padding(5.dp)
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorite",
                tint = MarvelRed,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { onFavoriteClick() }
            )
        }
    }
}

@Preview
@Composable
fun ComicCardPreview() {
    ComicCard(
        title = "Comic Title",
        thumbnail = "https://cdn.marvel.com/u/prod/marvel/i/mg/c/d0/66a3ee84e6e1e/portrait_uncanny.jpg",
        isFavorite = true,
        onFavoriteClick = {}
    )
}