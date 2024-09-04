package com.example.marvel.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marvel.R
import com.example.marvel.ui.theme.MarvelRed
import com.example.marvel.ui.theme.MarvelTheme
import com.example.marvel.utils.modifier.topBorder

@Composable
fun MarvelCard(title: String, url: String) {
    Column(
        Modifier
            .height(200.dp)
            .aspectRatio(0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.wolverine),
            contentDescription = "example image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
        )
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Black)
                .topBorder(2.dp, MarvelRed),
        ) {
            Text(
                text = title,
                fontSize = 9.sp,
                color = Color.White,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(8.dp)
            )

        }

    }
}


@Preview(showBackground = true)
@Composable
fun MarvelCardPreview() {
    MarvelTheme {
        MarvelCard(title = "WOLVERINE", url = "")
    }
}