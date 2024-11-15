package com.example.marvel.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.marvel.navigation.enums.Routes
import com.example.marvel.screens.ComicDetail
import com.example.marvel.screens.Comics
import com.example.marvel.screens.Favorites
import com.example.marvel.screens.HeroDetail
import com.example.marvel.screens.Heroes

@Composable
fun Router(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.Heroes.name,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 10.dp)
    ) {

        composable(route = Routes.Heroes.name) {
            Heroes(
                onNavigate = { route -> navController.navigate(route) }
            )
        }
        composable(route = Routes.Comics.name) {
            Comics(
                onNavigate = { route -> navController.navigate(route) }
            )
        }
        composable(route = Routes.Favorites.name) {
            Favorites(
                onNavigate = { route -> navController.navigate(route) }
            )
        }
        composable(route = "${Routes.HeroDetails.name}/{heroId}") { backStackEntry ->
            val heroId = backStackEntry.arguments?.getString("heroId") ?: ""
            HeroDetail(heroId)
        }
        composable(route = "${Routes.ComicDetail.name}/{heroId}") { backStackEntry ->
            val comicId = backStackEntry.arguments?.getString("heroId") ?: ""
            ComicDetail(comicId)
        }

    }
}