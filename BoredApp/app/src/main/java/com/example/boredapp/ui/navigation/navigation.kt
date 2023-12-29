package com.example.boredapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.boredapp.ui.Destinations
import com.example.boredapp.ui.createActivity.SelectCreateChoice
import com.example.boredapp.ui.home.HomeScreen
import com.example.boredapp.ui.profile.ProfileScreen

/**
 * Composable function representing the navigation component of the app.
 *
 * @param navController The [NavHostController] for handling navigation within the app.
 * @param modifier The [Modifier] for customizing the layout of the navigation component.
 */
@Composable
fun NavComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    // Set up navigation destinations using NavHost
    NavHost(
        navController = navController,
        startDestination = Destinations.Home.name,
        modifier = modifier,
    ) {
        // Home destination
        composable(Destinations.Home.name) {
            HomeScreen()
        }

        // Create destination
        composable(Destinations.Create.name) {
            SelectCreateChoice()
        }

        // Profile destination
        composable(Destinations.Profile.name) {
            ProfileScreen()
        }
    }
}
