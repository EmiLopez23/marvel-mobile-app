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
import com.example.marvel.screens.Comics
import com.example.marvel.screens.HeroDetail
import com.example.marvel.screens.Heroes
import com.example.marvel.screens.Profile

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
            Comics()
        }
        composable(route = Routes.Profile.name) {
            Profile()
        }
        composable(route = "${Routes.HeroDetails.name}/{heroId}") { backStackEntry ->
            val heroId = backStackEntry.arguments?.getString("heroId") ?: ""
            HeroDetail(heroId)
        }
    }
}