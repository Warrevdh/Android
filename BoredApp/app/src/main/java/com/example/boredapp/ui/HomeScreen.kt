package com.example.boredapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
//    val activities = mutableStateOf(data.Activity.getAll())
    val viewModel: ActivityViewModel = viewModel()
    val taskUiState by viewModel.activityUiState.collectAsState()
    val activities = taskUiState.activities
    Box(modifier = modifier) {
        LazyColumn {
            items(activities.size) { index ->
                val activity = activities[index]
                ActivityItem(
                    name = activity.name,
                    subtitle = activity.description,
                )
            }
        }
    }
}
