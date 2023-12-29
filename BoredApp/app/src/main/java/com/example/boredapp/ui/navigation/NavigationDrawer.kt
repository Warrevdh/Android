package com.example.boredapp.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavDestination
import com.example.boredapp.R

/**
 * Composable function representing the content of the navigation drawer.
 *
 * @param selectedDestination The currently selected navigation destination.
 * @param onTabPressed Callback function for when a tab is pressed.
 * @param onHomePressed Callback function for when the home tab is pressed.
 * @param modifier The [Modifier] for customizing the layout of the navigation drawer content.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerContent(
    selectedDestination: NavDestination?,
    onTabPressed: ((String) -> Unit),
    onHomePressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Column is used for vertically arranging navigation drawer items
    Column(modifier = modifier) {
        // Iterate through navigation items and create NavigationDrawerItem for each
        for (navItem in NavOptions.values()) {
            NavigationDrawerItem(
                // Check if the current navigation item is selected
                selected = selectedDestination?.route == navItem.name,
                // Label is a Text composable representing the name of the navigation item
                label = {
                    Text(
                        text = navItem.name,
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.drawer_padding_header)),
                    )
                },
                // Icon is an ImageVector representing the icon of the navigation item
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.name,
                    )
                },
                // Customize colors, setting unselectedContainerColor to transparent
                colors =
                    NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                    ),
                // Callback function for when a navigation item is clicked
                onClick = {
                    // Call the appropriate callback based on the clicked item
                    if (navItem.name == NavOptions.Home.name) {
                        onHomePressed()
                    } else {
                        onTabPressed(navItem.name)
                    }
                },
            )
        }
    }
}
