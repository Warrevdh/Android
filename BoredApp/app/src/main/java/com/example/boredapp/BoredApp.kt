package com.example.boredapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
enum class Destinations {
    Home,
    Create,
    Finished,
    About,
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
                if (isHomeDestination) {
                    IconButton(onClick = {
                        // show menu
                    }) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = "Menu",
                        )
                    }
                } else {
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
                { navController.navigate(Destinations.Finished.name) },
                { navController.navigate(Destinations.About.name) },
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
                CreateActivityItem {
                    navController.navigate(Destinations.Home.name)
                }
            }
            composable(Destinations.About.name) {
                Text("About")
            }
            composable(Destinations.Finished.name) {
                Text("Finished")
            }
        }
    }
}
