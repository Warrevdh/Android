package com.example.boredapp.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boredapp.model.Activity
import com.example.boredapp.ui.components.ActivityItem
import com.example.boredapp.ui.components.Dialog

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    activityViewModel: SavedActivitiesViewModel = viewModel(factory = SavedActivitiesViewModel.Factory),
) {
    val savedActivitiesState by activityViewModel.savedActivityList.collectAsState()
    val activityListState = activityViewModel.activityListState
    var deleteOne by remember { mutableStateOf(false) }
    var deleteAll by remember { mutableStateOf(false) }
    var confirmedItem: Activity? by remember { mutableStateOf(null) }
    var emptyList by remember { mutableStateOf(false) }

    LaunchedEffect(savedActivitiesState) {
        emptyList = savedActivitiesState.activities.isEmpty()
    }

    Box(modifier = modifier) {
        when (activityListState) {
            is ActivityListState.Loading -> {
                CircularProgressIndicator()
            }
            is ActivityListState.Success -> {
                if (emptyList) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = "Je hebt nog geen activiteiten opgeslagen")
                    }
                } else {
                    Column(modifier = modifier.fillMaxSize()) {
                        Button(
                            onClick = {
                                deleteAll = true
                            },
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                        ) {
                            Text(text = "Verwijder alle activiteiten")
                        }
                        LazyColumn {
                            items(savedActivitiesState.activities) {
                                ActivityItem(
                                    activity = it,
                                    inList = true,
                                    showConfirmation = {
                                        deleteOne = true
                                        confirmedItem = it
                                    },
                                )
                            }
                        }
                        if (deleteOne) {
                            Dialog(
                                closeDialog = {
                                    deleteOne = false
                                },
                                {
                                    confirmedItem?.let { confirmedActivity ->
                                        activityViewModel.deleteActivity(confirmedActivity)
                                    }
                                    deleteOne = false
                                    confirmedItem = null
                                },
                                title = "Verwijder activiteit",
                                text = "Weet je zeker dat je deze activiteit wilt verwijderen?",
                            )
                        }
                    }
                }
            }
            is ActivityListState.Error -> {
                Text(text = "Error")
            }
        }
    }

    if (deleteAll) {
        Dialog(
            closeDialog = {
                deleteAll = false
            },
            {
                activityViewModel.clearList()
                deleteAll = false
            },
            title = "Verwijder alle activiteiten",
            text = "Weet je zeker dat je alle activiteiten wilt verwijderen?",
        )
    }
}
