package com.example.boredapp.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boredapp.ui.components.ActivityItem

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    activityViewModel: SavedActivitiesViewModel = viewModel(factory = SavedActivitiesViewModel.Factory),
) {
    val savedActivitiesState by activityViewModel.savedActivityList.collectAsState()
    
    val activityListState = activityViewModel.activityListState

    Box(modifier = modifier) {
        when (activityListState) {
            is ActivityListState.Loading -> {
                CircularProgressIndicator()
            }
            is ActivityListState.Success -> {
                LazyColumn {
                    items(savedActivitiesState.activities) { 
                        ActivityItem(activity = it)
                    }
                }
            }
            is ActivityListState.Error -> {
                Text(text = "Error")
            }
            is ActivityListState.Empty -> {
                Text(text = "Empty")
            }
        }
    }
}
