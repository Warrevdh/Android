package com.example.boredapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.boredapp.R

enum class NavOptions(@StringRes val title: Int, val icon: ImageVector) {
    Home(title = R.string.app_name, icon = Icons.Filled.Home),
    Create(title = R.string.create, icon = Icons.Filled.Create),
    Profile(title = R.string.profile, icon = Icons.Filled.AccountCircle),
}