package com.example.boredapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoredApp() {
    var createNewActivity by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            MyTopAppBar()
        },
        bottomBar = {
            MyBottomAppBar()
        },

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            if (createNewActivity) {
                CreateActivityItem(onCancel = {
                    createNewActivity = false
                })
            } else {
                HomeScreen()
            }
        }
    }
}
