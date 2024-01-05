package com.example.boredapp.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import com.example.boredapp.ui.navigation.NavOptions

/**
 * Composable function representing a custom bottom app bar for navigation.
 *
 * @param selectedDestination The currently selected navigation destination.
 * @param onTabPressed Callback for handling tab press events.
 * @param onHomePressed Callback for handling the home button press event.
 */
@Composable
fun MyBottomAppBar(
    selectedDestination: NavDestination?,
    onTabPressed: (String) -> Unit,
    onHomePressed: () -> Unit,
) {
    NavigationBar {
        for (navItem in NavOptions.values()) {
            NavigationBarItem(
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
                        contentDescription = navItem.name + " icon",
                    )
                },
                label = { Text(text = navItem.name) },
                colors =
                    NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        indicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
            )
        }
    }
}
