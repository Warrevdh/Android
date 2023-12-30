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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boredapp.R
import com.example.boredapp.ui.createActivity.components.CreateActivityItem
import com.example.boredapp.ui.createActivity.components.CreateRangedSlider
import com.example.boredapp.ui.createActivity.components.CreateSlider
import com.example.boredapp.ui.createActivity.components.Dropdown

/**
 * Composable function for selecting and creating activities based on different criteria.
 *
 * @param generateActivityViewModel The [GenerateActivityViewModel] used for managing activity generation.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCreateChoice(generateActivityViewModel: GenerateActivityViewModel = viewModel(factory = GenerateActivityViewModel.Factory)) {
    // State variables for UI components
    var selectedOptionText by remember { mutableStateOf("") }
    var amountOfParticipants by remember { mutableStateOf("") }
    var priceSliderValue by remember { mutableFloatStateOf(0f) }
    var accSliderValue by remember { mutableFloatStateOf(0f) }
    var priceRangeSliderValue by remember { mutableStateOf(0f..1f) }
    var accRangeSliderValue by remember { mutableStateOf(0f..1f) }
    var selectedTab by remember { mutableIntStateOf(0) }

    // Context for displaying toasts
    val context = LocalContext.current

    // List of tabs for different criteria
    val tabs =
        listOf(
            "Random",
            "Type",
            "Participants",
            "Price",
            "Accessibility",
            "Price range",
            "Accessibility range",
        )

    // Reset API state when the selected tab changes
    LaunchedEffect(selectedTab) {
        generateActivityViewModel.resetApiState()
    }

    // Main column for UI layout
    Column {
        // Scrollable tabs for different criteria
        ScrollableTabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { tabIndex, tab ->
                Tab(
                    selected = selectedTab == tabIndex,
                    onClick = { selectedTab = tabIndex },
                    text = {
                        Text(text = tab)
                    },
                )
            }
        }

        // Vertical spacing
        Spacer(modifier = Modifier.height(16.dp))

        // Display UI components based on the selected tab
        when (selectedTab) {
            0 -> {
                // Random criteria
                CreateActivityItem(
                    generateActivityViewModel = generateActivityViewModel,
                    waitingComposable = {
                        Text(text = stringResource(R.string.randomActivity))
                    },
                ) {
                    generateActivityViewModel.getApiActivity()
                }
            }
            1 -> {
                // Type criteria
                CreateActivityItem(
                    generateActivityViewModel = generateActivityViewModel,
                    waitingComposable = {
                        Dropdown(selectedOptionText = selectedOptionText) {
                            selectedOptionText = it
                        }
                    },
                ) {
                    if (selectedOptionText.isBlank()) {
                        Toast.makeText(context, "Select type", Toast.LENGTH_SHORT).show()
                    } else {
                        generateActivityViewModel.getApiActivityByType(selectedOptionText)
                    }
                }
            }
            2 -> {
                // Participants criteria
                CreateActivityItem(
                    generateActivityViewModel = generateActivityViewModel,
                    waitingComposable = {
                        TextField(
                            value = amountOfParticipants,
                            label = { Text("Amount of participants") },
                            onValueChange = { amountOfParticipants = it },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        )
                    },
                ) {
                    if (amountOfParticipants.toIntOrNull() == null || amountOfParticipants.toInt() < 1) {
                        Toast.makeText(context, "Give a valid amount of participants", Toast.LENGTH_SHORT).show()
                        amountOfParticipants = ""
                    } else {
                        generateActivityViewModel.getApiActivityByParticipants(amountOfParticipants.toInt())
                    }
                }
            }
            3 -> {
                // Price criteria
                CreateActivityItem(
                    generateActivityViewModel = generateActivityViewModel,
                    waitingComposable = {
                        CreateSlider("Price: ", priceSliderValue, onValueChange = { priceSliderValue = it })
                    },
                ) {
                    generateActivityViewModel.getApiActivityByPrice(priceSliderValue)
                }
            }
            4 -> {
                // Accessibility criteria
                CreateActivityItem(
                    generateActivityViewModel = generateActivityViewModel,
                    waitingComposable = {
                        CreateSlider(
                            text = "Accessibility: ",
                            sliderValue = accSliderValue,
                            onValueChange = { accSliderValue = it },
                        )
                    },
                ) {
                    generateActivityViewModel.getApiActivityByAccessibility(accSliderValue)
                }
            }
            5 -> {
                // Price range criteria
                CreateActivityItem(
                    generateActivityViewModel = generateActivityViewModel,
                    waitingComposable = {
                        CreateRangedSlider(
                            text = "Price: ",
                            sliderValue = priceRangeSliderValue,
                            onValueChange = { priceRangeSliderValue = it },
                        )
                    },
                ) {
                    generateActivityViewModel.getApiActivityByPriceRange(priceRangeSliderValue.start, priceRangeSliderValue.endInclusive)
                }
            }
            6 -> {
                // Accessibility range criteria
                CreateActivityItem(
                    generateActivityViewModel = generateActivityViewModel,
                    waitingComposable = {
                        CreateRangedSlider(
                            text = "Accessibility: ",
                            sliderValue = accRangeSliderValue,
                            onValueChange = { accRangeSliderValue = it },
                        )
                    },
                ) {
                    generateActivityViewModel.getApiActivityByAccessibilityRange(
                        accRangeSliderValue.start,
                        accRangeSliderValue.endInclusive,
                    )
                }
            }
        }
    }
}
