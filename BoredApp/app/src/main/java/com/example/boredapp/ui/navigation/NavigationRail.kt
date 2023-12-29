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

/**
 * Composable function representing a custom navigation rail for the Bored app.
 *
 * @param selectedDestination The currently selected navigation destination.
 * @param onTabPressed Callback function for when a tab is pressed.
 * @param onHomePressed Callback function for when the home tab is pressed.
 * @param modifier The [Modifier] for customizing the layout of the navigation rail.
 */
@Composable
fun BoredNavigationRail(
    selectedDestination: NavDestination?,
    onTabPressed: (String) -> Unit,
    onHomePressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // NavigationRail is used for displaying the custom navigation rail
    NavigationRail(modifier = modifier) {
        // Iterate through navigation items and create NavigationRailItem for each
        for (navItem in NavOptions.values()) {
            NavigationRailItem(
                // Check if the current navigation item is selected
                selected = selectedDestination?.route == navItem.name,
                // Callback function for when a navigation item is clicked
                onClick = {
                    // Call the appropriate callback based on the clicked item
                    if (navItem.name == NavOptions.Home.name) {
                        onHomePressed()
                    } else {
                        onTabPressed(navItem.name)
                    }
                },
                // Icon is an ImageVector representing the icon of the navigation item
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.name,
                    )
                },
                // Customize colors, setting selectedIconColor and indicatorColor
                colors =
                    NavigationRailItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        indicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
            )
        }
    }
}
