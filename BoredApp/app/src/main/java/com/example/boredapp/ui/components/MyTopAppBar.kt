package com.example.boredapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource

/**
 * Composable function representing a custom top app bar with optional navigation back button.
 *
 * @param canNavigateBack Indicates whether the navigation back button should be visible.
 * @param navigateUp Callback function to be executed when the navigation back button is pressed.
 * @param currentScreenTitle The resource ID for the title of the current screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    currentScreenTitle: Int,
) {
    TopAppBar(
        colors =
            TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
        title = {
            Text(stringResource(id = currentScreenTitle), modifier = Modifier.testTag("screenTitle"))
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Navigate Back",
                    )
                }
            }
        },
    )
}
