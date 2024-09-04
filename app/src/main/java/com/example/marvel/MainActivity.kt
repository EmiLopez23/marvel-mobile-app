package com.example.marvel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.marvel.navigation.BottomBar
import com.example.marvel.navigation.Router
import com.example.marvel.ui.theme.MarvelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MarvelTheme {
                Surface {
                    Scaffold(
                        bottomBar = {
                            BottomBar(onNavigate = {
                                navController.navigate(it)
                            })
                        }
                    ) { it ->
                        Router(innerPadding = it, navController = navController)
                    }
                }
            }
        }
    }
}