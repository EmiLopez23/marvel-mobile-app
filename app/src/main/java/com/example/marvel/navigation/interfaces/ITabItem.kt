package com.example.marvel.navigation.interfaces

import androidx.compose.ui.graphics.painter.Painter

data class TabItem(
    val title: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter,
)