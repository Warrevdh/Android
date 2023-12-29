package com.example.boredapp.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination

@Composable
fun BoredNavigationRail(
    selectedDestination: NavDestination?,
    onTabPressed: (String) -> Unit,
    onHomePressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationRail(modifier = modifier) {
        for (navItem in NavOptions.values()) {
            NavigationRailItem(
                selected = selectedDestination?.route == navItem.name,
                onClick =
                    {
                        if (navItem.name == NavOptions.Home.name) {
                            onHomePressed()
                        } else {
                            onTabPressed(navItem.name)
                        }
                    },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.name,
                    )
                },
                colors =
                    NavigationRailItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        indicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
            )
        }
    }
}
