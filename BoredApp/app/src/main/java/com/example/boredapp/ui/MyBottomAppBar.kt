package com.example.boredapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MyBottomAppBar(
    onHome: () -> Unit,
    onCreate: () -> Unit,
    onFinished: () -> Unit,
    onAbout: () -> Unit,
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
        actions = {
            IconButton(onClick = onHome) {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Home",
                )
            }
            IconButton(onClick = onCreate) {
                Icon(
                    Icons.Filled.Create,
                    contentDescription = "Create",
                )
            }
            IconButton(onClick = onFinished) {
                Icon(
                    Icons.Filled.Check,
                    contentDescription = "Finished",
                )
            }
            IconButton(onClick = onAbout) {
                Icon(
                    Icons.Filled.AccountCircle,
                    contentDescription = "About",
                )
            }
        },
    )
}
