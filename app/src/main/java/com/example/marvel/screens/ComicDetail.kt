package com.example.marvel.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.marvel.R
import com.example.marvel.models.Comic
import com.example.marvel.ui.components.Loader
import com.example.marvel.ui.components.Retry
import com.example.marvel.ui.theme.MarvelRed
import com.example.marvel.utils.modifier.helpers.formatImageUrl
import com.example.marvel.utils.modifier.topBorder
import com.example.marvel.viewModels.ComicDetailViewModel

@Composable
fun ComicDetail(
    comicId: String,
    viewModel: ComicDetailViewModel = hiltViewModel(),
) {
    val comic by viewModel.comic.collectAsState()
    val loading by viewModel.loadingHero.collectAsState()
    val showRetry by viewModel.showRetry.collectAsState()

    LaunchedEffect(comicId) {
        viewModel.loadComicDetails(comicId)
    }

    when {
        loading -> {
            Loader()
        }

        showRetry -> {
            Retry(onClick = { viewModel.loadComicDetails(comicId) })
        }

        else -> {
            ComicDetailContent(comic)
        }
    }

}


@Composable
fun ComicDetailContent(comic: Comic) {
    val image = if (comic.thumbnail.path.contains("image_not_available")) {
        R.drawable.placeholder
    } else {
        formatImageUrl(comic.thumbnail.path, comic.thumbnail.extension)
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AsyncImage(
            model = image,
            contentDescription = comic.title + " thumbnail",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        )
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .topBorder(2.dp, MarvelRed),
        ) {
            Text(
                text = comic.title,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.tertiary,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(16.dp)
            )
            Text(
                text = if (comic.description != null && comic.description != "") comic.description else stringResource(
                    id = R.string.no_description
                ),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.tertiary,
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