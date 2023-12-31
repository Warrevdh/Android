package com.example.boredapp.ui.createActivity.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.boredapp.ui.components.ActivityItem
import com.example.boredapp.ui.createActivity.ActivityApiState
import com.example.boredapp.ui.createActivity.GenerateActivityViewModel

/**
 * Composable function representing an item for creating and displaying an activity.
 *
 * @param modifier Modifier for styling and layout customization.
 * @param generateActivityViewModel ViewModel for handling the generation of activities.
 * @param waitingComposable Composable function to display content while waiting for the activity.
 * @param onGenerate Callback function to be executed when the "Generate" button is pressed.
 */
@Composable
fun CreateActivityItem(
    modifier: Modifier = Modifier,
    generateActivityViewModel: GenerateActivityViewModel,
    waitingComposable: @Composable () -> Unit,
    onGenerate: () -> Unit,
) {
    val uiActivityState by generateActivityViewModel.uiState.collectAsState()
    val activityApiState = generateActivityViewModel.activityApiState

    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box {
            when (activityApiState) {
                is ActivityApiState.Loading -> {
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                    CircularProgressIndicator(modifier = Modifier.testTag("loadingIcon"))
                }
                is ActivityApiState.Success -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        ActivityItem(activity = uiActivityState.activity)
                    }
                }
                is ActivityApiState.NoActivityFound -> {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        waitingComposable()
                        Text("No activity found, try again")
                    }
                }
                is ActivityApiState.Error -> {
                    Text(
                        "Something went wrong, make sure you have an internet connection",
                        modifier = Modifier.padding(horizontal = 16.dp),
                    )
                }
                is ActivityApiState.Waiting -> {
                    waitingComposable()
                }
            }
        }
        Column(
            modifier =
                modifier
                    .fillMaxHeight()
                    .padding(bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            when (activityApiState) {
                is ActivityApiState.Success -> {
                    SaveActivityBtns(generateActivityViewModel = generateActivityViewModel)
                }
                else -> {
                    Button(modifier = modifier.padding(vertical = 16.dp), onClick = onGenerate) {
                        Text("Generate")
                    }
                }
            }
        }
    }
}
