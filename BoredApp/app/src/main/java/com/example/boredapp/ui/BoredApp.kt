package com.example.boredapp.ui

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.boredapp.R
import com.example.boredapp.ui.components.MyBottomAppBar
import com.example.boredapp.ui.components.MyTopAppBar
import com.example.boredapp.ui.createActivity.SelectCreateChoice
import com.example.boredapp.ui.navigation.BoredNavigationRail
import com.example.boredapp.ui.navigation.NavOptions
import com.example.boredapp.ui.navigation.NavigationDrawerContent
import com.example.boredapp.ui.navigation.navComponent
import com.example.boredapp.ui.profile.ProfileScreen
import com.example.boredapp.ui.util.NavigationType

enum class Destinations {
    Home,
    Create,
    Profile,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoredApp(navigationType: NavigationType, navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val canNavigateBack = navController.previousBackStackEntry != null
    val navigateUp: () -> Unit = { navController.navigateUp() }
    val goHome: () -> Unit = {
        navController.popBackStack(
            NavOptions.Home.name,
            inclusive = false,
        )
    }
    val goToCreate = { navController.navigate(NavOptions.Create.name)  {launchSingleTop = true} }
    val goToProfile = { navController.navigate(NavOptions.Profile.name)  {launchSingleTop = true} }

    val currentScreenTitle = NavOptions.valueOf(
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
                        goHome,
                        goToCreate,
                        goToProfile,
                    )
                },
            ) { innerPadding ->
                navComponent(navController = navController, modifier = Modifier.padding(innerPadding))
            }
        }
        NavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            PermanentNavigationDrawer(drawerContent = {
                PermanentDrawerSheet(Modifier.width(dimensionResource(R.dimen.drawer_width))) {
                    NavigationDrawerContent(
                        selectedDestination = navController.currentDestination,
                        onTabPressed = {node: String -> navController.navigate(node)},
                        modifier = Modifier
                            .wrapContentWidth()
                            .fillMaxHeight()
                            .padding(dimensionResource(R.dimen.drawer_padding_content))
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
                    navComponent(navController = navController, modifier = Modifier.padding(innerPadding))
                }
            }
        }
        else -> {
            Row {
                AnimatedVisibility(visible = navigationType == NavigationType.NAVIGATION_RAIL) {
                    BoredNavigationRail(
                        selectedDestination = navController.currentDestination,
                        onTabPressed = { node: String -> navController.navigate(node) },
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
                    navComponent(navController = navController, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
