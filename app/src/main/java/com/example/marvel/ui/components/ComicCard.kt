package com.example.marvel.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ComicCard(
    title: String,
    thumbnail: String
) {
    Column {
        AsyncImage(
            model = thumbnail,
            contentDescription = "$title thumbnail",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
        )
        Text(
            text = title,
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(vertical = 16.dp)
        )
    }
}

@Preview
@Composable
fun ComicCardPreview() {
    ComicCard(
        title = "Comic Title",
        thumbnail = "https://cdn.marvel.com/u/prod/marvel/i/mg/c/d0/66a3ee84e6e1e/portrait_uncanny.jpg"
    )
}