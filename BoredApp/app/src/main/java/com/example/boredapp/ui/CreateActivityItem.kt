package com.example.boredapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateActivityItem(generateActivityViewModel: GenerateActivityViewModel, modifier: Modifier = Modifier) { 
    val generateActivityState by generateActivityViewModel.uiState.collectAsState()
    
    val activityApiState = generateActivityViewModel.activityApiState
    
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = modifier) {
            when (activityApiState) {
                is ActivityApiState.Loading -> {
                    Text("Loading...")
                }
                is ActivityApiState.Success -> {
                    GeneratedActivity(generateActivityState = generateActivityState)
                }
                is ActivityApiState.Error -> {
                    Text("Error")
                }
                is ActivityApiState.Waiting -> {}
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 8.dp), 
            verticalArrangement = Arrangement.Bottom,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                when (activityApiState) {
                    is ActivityApiState.Loading -> {
                        Button(onClick = { generateActivityViewModel.generateActivity() }) {
                            Text("Genereer")
                        }
                    }
                    is ActivityApiState.Success -> {
                        Row() {
                            Button(onClick = { 
                                generateActivityViewModel.resetActivity()
                            }) {
                                Text("Annuleer")
                            }
                            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                            Button(onClick = {
                                generateActivityViewModel.addActivity()
                            }) {
                                Text("Opslaan")
                            }
                        }
                    }
                    is ActivityApiState.Error -> {
                        Button(onClick = { generateActivityViewModel.generateActivity() }) {
                            Text("Genereer")
                        }
                    }
                    is ActivityApiState.Waiting -> {
                        Button(onClick = { generateActivityViewModel.generateActivity() }) {
                            Text("Genereer")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GeneratedActivity(modifier: Modifier = Modifier, generateActivityState: GenerateActivityState) {
    Column(modifier = modifier) {
        ActivityItem(
            activity = generateActivityState.activity.activity,
            type = generateActivityState.activity.type,
            participants = generateActivityState.activity.participants,
            price = generateActivityState.activity.price,
            accessibility = generateActivityState.activity.accessibility,
            link = generateActivityState.activity.link,
        )
    }
}
