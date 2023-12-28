package com.example.boredapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.boredapp.ui.Destinations
import com.example.boredapp.ui.home.HomeScreen
import com.example.boredapp.ui.createActivity.SelectCreateChoice
import com.example.boredapp.ui.profile.ProfileScreen

@Composable
fun navComponent(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Home.name,
        modifier = modifier,
    ) {
        composable(Destinations.Home.name) {
            HomeScreen()
        }
        composable(Destinations.Create.name) {
            SelectCreateChoice()
        }
        composable(Destinations.Profile.name) {
            ProfileScreen()
        }
    }
}