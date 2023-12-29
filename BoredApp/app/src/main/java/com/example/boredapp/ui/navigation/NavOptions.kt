package com.example.boredapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.boredapp.R

/**
 * Enum representing different navigation options in the application.
 *
 * @property title The resource ID for the title associated with the navigation option.
 * @property icon The vector image representing the icon associated with the navigation option.
 */
enum class NavOptions(
    @StringRes val title: Int,
    val icon: ImageVector,
) {
    /**
     * Home navigation option.
     */
    Home(title = R.string.app_name, icon = Icons.Filled.Home),

    /**
     * Create navigation option.
     */
    Create(title = R.string.create, icon = Icons.Filled.Create),

    /**
     * Profile navigation option.
     */
    Profile(title = R.string.profile, icon = Icons.Filled.AccountCircle),
}
