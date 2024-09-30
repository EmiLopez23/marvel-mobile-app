package com.example.marvel.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.outlined.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvel.R
import com.example.marvel.navigation.enums.Routes
import com.example.marvel.navigation.interfaces.TabItem

@Composable
fun BottomBar(
    onNavigate: (String) -> Unit
) {
    val heroesTab = TabItem(
        title = Routes.Heroes.name,
        unselectedIcon = rememberVectorPainter(Icons.Outlined.Face),
        selectedIcon = rememberVectorPainter(Icons.Filled.Face)
    )

    val comicsTab = TabItem(
        title = Routes.Comics.name,
        unselectedIcon = painterResource(id = R.drawable.book_outlined),
        selectedIcon = painterResource(id = R.drawable.book_filled)
    )

    val favoritesTab = TabItem(
        title = Routes.Favorites.name,
        unselectedIcon = painterResource(id = R.drawable.bookmark_outlined),
        selectedIcon = painterResource(id = R.drawable.bookmark_filled)
    )

    val tabs = listOf(heroesTab, comicsTab, favoritesTab)

    TabsView(tabs = tabs, onNavigate = onNavigate)
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBar(onNavigate = {})
}