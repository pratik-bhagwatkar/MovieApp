package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.screens.details.DetailsScreen
import com.example.movieapp.screens.home.HomeScreen

@Composable
fun MovieNavigation(){
    val navController= rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationSealed.HomeScreen.route) {
        composable(
            route = NavigationSealed.HomeScreen.route,
        ) {
            HomeScreen(navController)
        }

        composable(
            route = NavigationSealed.DetailScreen.route,
        ) {
            val data = it.arguments?.getString("data")
            DetailsScreen(navController = navController, id = data!!)
        }
    }

}