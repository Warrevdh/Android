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
import androidx.compose.ui.unit.dp
import com.example.boredapp.ui.components.ActivityItem
import com.example.boredapp.ui.createActivity.ActivityApiState
import com.example.boredapp.ui.createActivity.GenerateActivityViewModel

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
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box {
            when (activityApiState) {
                is ActivityApiState.Loading -> {
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                    CircularProgressIndicator()
                }
                is ActivityApiState.Success -> {
                    ActivityItem(activity = uiActivityState.activity)
                }
                is ActivityApiState.NoActivityFound -> {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        waitingComposable()
                        Text("Geen activiteit gevonden")
                    }
                }
                is ActivityApiState.Error -> {
                    Text("Error")
                }
                is ActivityApiState.Waiting -> {
                    waitingComposable()
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp), 
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (activityApiState) {
                is ActivityApiState.Success -> {
                    SaveActivityBtns(generateActivityViewModel = generateActivityViewModel)
                }
                else -> {
                    Button(onClick = onGenerate) {
                        Text("Genereer")
                    }
                }
            }
        }
    }
}
