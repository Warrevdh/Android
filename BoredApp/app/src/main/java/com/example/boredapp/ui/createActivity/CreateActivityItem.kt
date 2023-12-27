package com.example.boredapp.ui.createActivity

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boredapp.ui.components.ActivityItem

@Composable
fun CreateActivityItem(
    modifier: Modifier = Modifier,
    generateActivityViewModel: GenerateActivityViewModel = viewModel(factory = GenerateActivityViewModel.Factory),
) {
    val uiActivityState by generateActivityViewModel.uiState.collectAsState()
    
    val activityApiState = generateActivityViewModel.activityApiState
    val saveActivityState = generateActivityViewModel.saveActivityState

    val context = LocalContext.current
    
    LaunchedEffect(saveActivityState) {
        when (saveActivityState) {
            SaveActivityState.Error -> {
                Toast.makeText(context, "De activiteit kon niet worden opgeslaan", Toast.LENGTH_SHORT).show()
            }
            SaveActivityState.Loading -> {}
            SaveActivityState.Success -> {
                Toast.makeText(context, "Activiteit opgeslaan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = modifier) {
            when (activityApiState) {
                is ActivityApiState.Loading -> {
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                    CircularProgressIndicator()
                }
                is ActivityApiState.Success -> {
                    GeneratedActivity(activityState = uiActivityState)
                }
                is ActivityApiState.Error -> {
                    Text("Error")
                }
                is ActivityApiState.Waiting -> {
                }
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
                    is ActivityApiState.Success -> {
                        Row() {
                            Button(onClick = { 
                                generateActivityViewModel.resetActivity()
                            }) {
                                Text("Annuleer")
                            }
                            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                            Button(onClick = {
                                generateActivityViewModel.saveActivity()
                            }) {
                                Text("Opslaan")
                            }
                        }
                    }
                    else -> {
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
fun GeneratedActivity(activityState: GenerateActivityState) {
    ActivityItem(
        activityState.activity,
    )
}
