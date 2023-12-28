package com.example.boredapp.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination

@Composable
fun BoredNavigationRail(selectedDestination: NavDestination?, onTabPressed: (String) -> Unit, modifier : Modifier = Modifier) {
    NavigationRail(modifier = modifier) {
        for (navItem in NavOptions.values()) {
            NavigationRailItem(
                selected = selectedDestination?.route == navItem.name,
                onClick = { onTabPressed(navItem.name) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.name
                    )
                }
            )
        }
    }
}