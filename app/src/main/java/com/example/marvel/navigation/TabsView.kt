package com.example.marvel.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.marvel.navigation.interfaces.TabItem
import com.example.marvel.ui.theme.MarvelRedTransparent

@Composable
fun TabsView(tabs: List<TabItem>, onNavigate: (String) -> Unit) {
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                icon = {
                    IconView(
                        isSelected = index == selectedTabIndex,
                        selectedIcon = tab.selectedIcon,
                        unselectedIcon = tab.unselectedIcon,
                        description = tab.title
                    )
                },
                label = { Text(tab.title) },
                selected = index == selectedTabIndex,
                onClick = {
                    selectedTabIndex = index
                    onNavigate(tab.title)
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MarvelRedTransparent,
//                    selectedIconColor = MarvelRed
                )
            )
        }
    }
}

@Composable
fun IconView(
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    description: String
) {
    Icon(
        imageVector = if (isSelected) selectedIcon else unselectedIcon,
        contentDescription = description
    )
}