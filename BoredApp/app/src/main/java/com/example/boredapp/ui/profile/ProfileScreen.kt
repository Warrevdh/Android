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

/**
 * Composable function representing the profile screen, displaying saved activities.
 *
 * @param modifier Modifier to be applied to the root layout.
 * @param activityViewModel ViewModel for managing saved activities.
 */
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    activityViewModel: SavedActivitiesViewModel = viewModel(factory = SavedActivitiesViewModel.Factory),
) {
    // Collecting the state of saved activities using a StateFlow
    val savedActivitiesState by activityViewModel.savedActivityList.collectAsState()

    // Getting the current state of activity list from the view model
    val activityListState = activityViewModel.activityListState

    // Variables to manage delete confirmation for a single item or all items
    var deleteOne by remember { mutableStateOf(false) }
    var deleteAll by remember { mutableStateOf(false) }
    var confirmedItem: Activity? by remember { mutableStateOf(null) }

    // Flag to check if the saved activities list is empty
    var emptyList by remember { mutableStateOf(false) }

    // Effect to check if the saved activities list is empty when it changes
    LaunchedEffect(savedActivitiesState) {
        emptyList = savedActivitiesState.activities.isEmpty()
    }

    // Main layout
    Box(modifier = modifier) {
        // Handling different states of the activity list
        when (activityListState) {
            // Loading state
            is ActivityListState.Loading -> {
                // Display a loading indicator
                CircularProgressIndicator()
            }

            // Success state
            is ActivityListState.Success -> {
                // Check if the saved activities list is empty
                if (emptyList) {
                    // Display a message if the list is empty
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = "Je hebt nog geen activiteiten opgeslagen")
                    }
                } else {
                    // Display the list of saved activities
                    Column(modifier = modifier.fillMaxSize()) {
                        // Button to delete all activities
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

                        // LazyColumn to display the list of saved activities
                        LazyColumn {
                            items(savedActivitiesState.activities) {
                                // Display each activity item
                                ActivityItem(
                                    activity = it,
                                    inList = true,
                                    showConfirmation = {
                                        // Set up confirmation for deleting a single item
                                        deleteOne = true
                                        confirmedItem = it
                                    },
                                )
                            }
                        }

                        // Dialog for confirming deletion of a single item
                        if (deleteOne) {
                            Dialog(
                                closeDialog = {
                                    deleteOne = false
                                },
                                {
                                    confirmedItem?.let { confirmedActivity ->
                                        // Delete the confirmed activity
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

            // Error state
            is ActivityListState.Error -> {
                // Display an error message
                Text(text = "Error")
            }
        }
    }

    // Dialog for confirming deletion of all activities
    if (deleteAll) {
        Dialog(
            closeDialog = {
                deleteAll = false
            },
            {
                // Clear the list of saved activities
                activityViewModel.clearList()
                deleteAll = false
            },
            title = "Verwijder alle activiteiten",
            text = "Weet je zeker dat je alle activiteiten wilt verwijderen?",
        )
    }
}
