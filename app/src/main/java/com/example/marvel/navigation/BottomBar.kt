package com.example.marvel.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvel.navigation.enums.Routes
import com.example.marvel.navigation.interfaces.TabItem

@Composable
fun BottomBar(
    onNavigate: (String) -> Unit
) {
    val heroesTab = TabItem(
        title = Routes.Heroes.name,
        unselectedIcon = Icons.Outlined.Face,
        selectedIcon = Icons.Filled.Face
    )

    val comicsTab = TabItem(
        title = Routes.Comics.name,
        unselectedIcon = Icons.Outlined.MailOutline,
        selectedIcon = Icons.Filled.MailOutline
    )

    val profileTab = TabItem(
        title = Routes.Profile.name,
        unselectedIcon = Icons.Outlined.AccountCircle,
        selectedIcon = Icons.Filled.AccountCircle
    )

    val tabs = listOf(heroesTab, comicsTab, profileTab)

    TabsView(tabs = tabs, onNavigate = onNavigate)
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBar(onNavigate = {})
}