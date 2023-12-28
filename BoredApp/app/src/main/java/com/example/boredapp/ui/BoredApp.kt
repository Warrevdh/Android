package com.example.boredapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.boredapp.ui.components.MyBottomAppBar
import com.example.boredapp.ui.components.MyTopAppBar
import com.example.boredapp.ui.createActivity.SelectCreateChoice
import com.example.boredapp.ui.profile.ProfileScreen

enum class Destinations {
    Home,
    Create,
    Profile,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoredApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            MyTopAppBar {
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val isHomeDestination = currentBackStackEntry?.destination?.route == Destinations.Home.name
                if (!isHomeDestination) {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            }
        },
        bottomBar = {
            MyBottomAppBar(
                { navController.popBackStack(Destinations.Home.name, false) },
                { navController.navigate(Destinations.Create.name) },
                { navController.navigate(Destinations.Profile.name) },
            )
        },
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Destinations.Home.name,
            Modifier.padding(innerPadding),
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
}
