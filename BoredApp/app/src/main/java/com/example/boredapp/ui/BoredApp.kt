package com.example.boredapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.boredapp.R
import com.example.boredapp.ui.components.MyBottomAppBar
import com.example.boredapp.ui.components.MyTopAppBar
import com.example.boredapp.ui.navigation.BoredNavigationRail
import com.example.boredapp.ui.navigation.NavComponent
import com.example.boredapp.ui.navigation.NavOptions
import com.example.boredapp.ui.navigation.NavigationDrawerContent
import com.example.boredapp.ui.navigation.NavigationType

enum class Destinations {
    Home,
    Create,
    Profile,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoredApp(
    navigationType: NavigationType,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val canNavigateBack = navController.previousBackStackEntry != null
    val navigateUp: () -> Unit = { navController.navigateUp() }
    val onHomePressed: () -> Unit = { navController.popBackStack(NavOptions.Home.name, inclusive = false) }
    val onTabPressed: (String) -> Unit = { node: String -> navController.navigate(node) { launchSingleTop = true } }
    val currentScreenTitle =
        NavOptions.valueOf(
            backStackEntry?.destination?.route ?: NavOptions.Home.name,
        ).title

    when (navigationType) {
        NavigationType.BOTTOM_NAVIGATION -> {
            Scaffold(
                topBar = {
                    MyTopAppBar(
                        canNavigateBack = canNavigateBack,
                        navigateUp = navigateUp,
                        currentScreenTitle = currentScreenTitle,
                    )
                },
                bottomBar = {
                    MyBottomAppBar(
                        selectedDestination = navController.currentDestination,
                        onTabPressed,
                        onHomePressed,
                    )
                },
            ) { innerPadding ->
                NavComponent(navController = navController, modifier = Modifier.padding(innerPadding))
            }
        }
        NavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            PermanentNavigationDrawer(drawerContent = {
                PermanentDrawerSheet(Modifier.width(dimensionResource(R.dimen.drawer_width))) {
                    NavigationDrawerContent(
                        selectedDestination = navController.currentDestination,
                        onTabPressed,
                        onHomePressed,
                        modifier =
                            Modifier
                                .wrapContentWidth()
                                .fillMaxHeight()
                                .padding(dimensionResource(R.dimen.drawer_padding_content)),
                    )
                }
            }) {
                Scaffold(
                    topBar = {
                        MyTopAppBar(
                            canNavigateBack = canNavigateBack,
                            navigateUp = navigateUp,
                            currentScreenTitle = currentScreenTitle,
                        )
                    },
                ) { innerPadding ->
                    NavComponent(navController = navController, modifier = Modifier.padding(innerPadding))
                }
            }
        }
        else -> {
            Row {
                AnimatedVisibility(visible = navigationType == NavigationType.NAVIGATION_RAIL) {
                    BoredNavigationRail(
                        selectedDestination = navController.currentDestination,
                        onTabPressed,
                        onHomePressed,
                    )
                }
                Scaffold(
                    topBar = {
                        MyTopAppBar(
                            canNavigateBack = canNavigateBack,
                            navigateUp = navigateUp,
                            currentScreenTitle = currentScreenTitle,
                        )
                    },
                ) { innerPadding ->
                    NavComponent(navController = navController, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
