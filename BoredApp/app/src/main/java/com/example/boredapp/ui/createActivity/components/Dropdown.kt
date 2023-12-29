package com.example.boredapp.ui.createActivity.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

/**
 * Composable function representing a dropdown menu for selecting options.
 *
 * @param selectedOptionText The currently selected option's text.
 * @param onValueChange Callback function to be executed when the selected option changes.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dropdown(
    selectedOptionText: String,
    onValueChange: (String) -> Unit,
) {
    // List of available options for the dropdown
    val options = listOf("education", "recreational", "social", "diy", "charity", "cooking", "relaxation", "music", "busywork")

    // State to track the dropdown's expanded/collapsed state
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = it
        },
    ) {
        TextField(
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { onValueChange(it) },
            label = { Text("Choose a type") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded,
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
        ) {
            // Create a dropdown menu item for each option
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(text = selectionOption) },
                    onClick = {
                        onValueChange(selectionOption)
                        expanded = false
                    },
                )
            }
        }
    }
}
