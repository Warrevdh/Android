package com.example.boredapp.ui.createActivity

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boredapp.ui.createActivity.components.CreateActivityItem
import com.example.boredapp.ui.createActivity.components.CreateRangedSlider
import com.example.boredapp.ui.createActivity.components.CreateSlider
import com.example.boredapp.ui.createActivity.components.Dropdown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCreateChoice(
    generateActivityViewModel: GenerateActivityViewModel = viewModel(factory = GenerateActivityViewModel.Factory),
) {
    var selectedOptionText by remember { mutableStateOf("") }
    var amountOfParticipants by remember { mutableStateOf("") }
    var priceSliderValue by remember { mutableFloatStateOf(0f) }
    var accSliderValue by remember { mutableFloatStateOf(0f) }
    var priceRangeSliderValue by remember { mutableStateOf(0f..1f) }
    var accRangeSliderValue by remember { mutableStateOf(0f..1f) }
    var selectedTab by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    val tabs = listOf(
        "Type",
        "Deelnemers",
        "Prijs",
        "Toegankelijkheid",
        "Prijsrange",
        "Toegankelijkheid range",
    )
    
    LaunchedEffect(selectedTab) {
        generateActivityViewModel.resetApiState()
    }

    Column {
        ScrollableTabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { tabIndex, tab ->
                Tab(selected = selectedTab == tabIndex, onClick = { selectedTab = tabIndex }, text = {
                    Text(
                        text = tab,
                    )
                })
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (selectedTab) {
            0 -> {
                CreateActivityItem(
                    generateActivityViewModel = generateActivityViewModel,
                    waitingComposable = {
                        Dropdown(
                            selectedOptionText = selectedOptionText,
                        ) {
                            selectedOptionText = it
                        }
                    },
                ) {
                    if (selectedOptionText.isBlank()) {
                        Toast.makeText(context, "Selecteer een type", Toast.LENGTH_SHORT).show()
                    } else {
                        generateActivityViewModel.getApiActivityByType(selectedOptionText)
                    }
                }
            }
            1 -> {
                CreateActivityItem(
                    generateActivityViewModel = generateActivityViewModel,
                    waitingComposable = { 
                        TextField(
                            value = amountOfParticipants,
                            label = { Text("Aantal deelnemers") },
                            onValueChange = { amountOfParticipants = it }, 
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        )
                    },
                ) {
                    if (amountOfParticipants.toIntOrNull() == null || amountOfParticipants.toInt() < 1) {
                        Toast.makeText(context, "Geef een geldig aantal deelnemers", Toast.LENGTH_SHORT).show()
                        amountOfParticipants = ""
                    } else {
                        generateActivityViewModel.getApiActivityByParticipants(amountOfParticipants.toInt())
                    }
                }
            }
            2 -> {
                CreateActivityItem(
                    generateActivityViewModel = generateActivityViewModel,
                    waitingComposable = {
                        CreateSlider("Prijs: ", priceSliderValue, onValueChange = { priceSliderValue = it })
                    },
                ) {
                    generateActivityViewModel.getApiActivityByPrice(priceSliderValue)
                }
            }
            3 -> {
                CreateActivityItem(
                    generateActivityViewModel = generateActivityViewModel,
                    waitingComposable = { CreateSlider(text = "Toegankelijkheid", sliderValue = accSliderValue, onValueChange = { accSliderValue = it }) },
                ) {
                    generateActivityViewModel.getApiActivityByAccessibility(accSliderValue)
                }
            }
            4 -> {
                CreateActivityItem(
                    generateActivityViewModel = generateActivityViewModel,
                    waitingComposable = { 
                        CreateRangedSlider(
                            text = "Prijs",
                            sliderValue = priceRangeSliderValue,
                            onValueChange = { priceRangeSliderValue = it },
                        )
                    },
                ) {
                    generateActivityViewModel.getApiActivityByPriceRange(priceRangeSliderValue.start, priceRangeSliderValue.endInclusive)
                }
            }
            5 -> {
                CreateActivityItem(
                    generateActivityViewModel = generateActivityViewModel,
                    waitingComposable = { 
                        CreateRangedSlider(
                            text = "Toegankelijkheid",
                            sliderValue = accRangeSliderValue,
                            onValueChange = { accRangeSliderValue = it },
                        )
                    },
                ) {
                    generateActivityViewModel.getApiActivityByAccessibilityRange(accRangeSliderValue.start, accRangeSliderValue.endInclusive)
                }
            }
        }
    }
}
