package com.example.marvel.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.marvel.models.Hero
import com.example.marvel.ui.theme.MarvelRed
import com.example.marvel.utils.modifier.topBorder

@Composable
fun MarvelCard(hero: Hero) {
    //Marvel API does not retrieve thumbnails with https, so we need to replace it (they are actually available with https)
    val imageUrl = hero.thumbnail.path.replace("http", "https") + "." + hero.thumbnail.extension
    Column(
        Modifier
            .aspectRatio(2f)
            .clip(RoundedCornerShape(16.dp))
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = hero.name + " thumbnail",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        )
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Black)
                .topBorder(2.dp, MarvelRed),
        ) {
            Text(
                text = hero.name,
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(16.dp)
            )

        }

    }
}