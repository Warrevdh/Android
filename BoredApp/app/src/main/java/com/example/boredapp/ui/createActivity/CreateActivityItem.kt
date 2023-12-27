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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boredapp.ui.components.ActivityItem
import com.example.boredapp.ui.createActivity.components.CreateFunctions

@Composable
fun CreateActivityItem(
    modifier: Modifier = Modifier,
    generateActivityViewModel: GenerateActivityViewModel = viewModel(factory = GenerateActivityViewModel.Factory),
) {
    val uiActivityState by generateActivityViewModel.uiState.collectAsState()
    val activityApiState = generateActivityViewModel.activityApiState
    val context = LocalContext.current
    
    var checkedType by remember { mutableStateOf(false) }
    var checkedAccessibility by remember { mutableStateOf(false) }
    var checkedPrice by remember { mutableStateOf(false) }
    var checkedParticipants by remember { mutableStateOf(false) }
    var dropdownAccessibility by remember { mutableStateOf(false) }
    var dropdownPrice by remember { mutableStateOf(false) }
    var accesibilitySliderValue by remember { mutableFloatStateOf(0.0f) }
    var accesibilitySliderValues by remember { mutableStateOf(0.0f..1.0f) }
    var priceSlidesValue by remember { mutableFloatStateOf(0.0f) }
    var priceSlidesValues by remember { mutableStateOf(0.0f..1.0f) }
    
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
                    Column {
                        Row {
                            CreateFunctions(
                                checkedType = checkedType,
                                checkedAccessibility = checkedAccessibility,
                                checkedPrice = checkedPrice,
                                checkedParticipants = checkedParticipants,
                                dropdownAccessibility = dropdownAccessibility,
                                dropdownPrice = dropdownPrice,
                                accesibilitySliderValue = accesibilitySliderValue,
                                accesibilitySliderValues = accesibilitySliderValues,
                                priceSlidesValue = priceSlidesValue,
                                priceSlidesValues = priceSlidesValues,
                                onDropdownCheckedChange = {
                                    checkedType = it
                                    if (checkedType) {
                                        checkedAccessibility = false
                                        checkedPrice = false
                                        checkedParticipants = false
                                    } 
                                },
                                onSliderOneCheckedChange = { 
                                    checkedAccessibility = it 
                                    if (checkedAccessibility) {
                                        checkedType = false
                                        checkedPrice = false
                                        checkedParticipants = false
                                    }
                                },
                                openDropdownOne = { dropdownAccessibility = true },
                                dismissDropdownOne = { dropdownAccessibility = false },
                                onAccSliderValueChange = { accesibilitySliderValue = it },
                                onAccSliderValuesChange = { accesibilitySliderValues = it },
                                onSliderTwoCheckedChange = {
                                    checkedPrice = it 
                                    if (checkedPrice) {
                                        checkedType = false
                                        checkedAccessibility = false
                                        checkedParticipants = false
                                    } 
                                },
                                openDropdownTwo = { dropdownPrice = true },
                                dimissDropdownTwo = { dropdownPrice = false },
                                onPriceSliderValueChange = { priceSlidesValue = it },
                                onPriceSliderValiesChange = { priceSlidesValues = it },
                                onCheckedPartChange = { 
                                    checkedParticipants = it 
                                    if (checkedParticipants) {
                                        checkedType = false
                                        checkedAccessibility = false
                                        checkedPrice = false
                                    }
                                },
                            )
                        }
                    } 
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
                    Row() {
                        Button(onClick = { 
                            generateActivityViewModel.resetActivity()
                        }) {
                            Text("Annuleer")
                        }
                        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                        Button(onClick = {
                            generateActivityViewModel.saveActivity()
                            Toast.makeText(context, "Activiteit opgeslaan", Toast.LENGTH_SHORT).show()
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

@Composable
fun GeneratedActivity(activityState: GenerateActivityState) {
    ActivityItem(
        activityState.activity,
    )
}
