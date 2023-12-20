package com.example.boredapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProfileScreen(
    activityViewModel: SavedActivitiesViewModel = viewModel(),
) {
    val activityState by activityViewModel.uiState.collectAsState()
    val activities = activityState.activities
//    LazyColumn {
//        items(activities.size) { index ->
//            val activity = activities[index]
//            ActivityItem(
//                name = activity.activity,
//                subtitle = activity.type,
//            )
//        }
//    }
}
