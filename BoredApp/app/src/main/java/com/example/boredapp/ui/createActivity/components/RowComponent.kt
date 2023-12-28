package com.example.boredapp.ui.createActivity.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RowComponent(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    openDropdown: () -> Unit,
    dropdown: Boolean,
    dismissDropdown: () -> Unit,
    sliderValue: Float,
    sliderValues: ClosedFloatingPointRange<Float>,
    onSliderValueChange: (Float) -> Unit,
    onSliderValuesChange: (ClosedFloatingPointRange<Float>) -> Unit,
    text: String,
    showSpecific: Boolean,
    onClickSpecific: () -> Unit,
    onClickRanged: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Column {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
            )
            Box {
                IconButton(
                    enabled = checked,
                    onClick = openDropdown,
                ) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
                DropdownMenu(
                    expanded = dropdown,
                    onDismissRequest = dismissDropdown,
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "Specifiek") },
                        onClick = onClickSpecific,
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Ranged") },
                        onClick = onClickRanged,
                    )
                }
            }
        }
        if (showSpecific) {
            CreateSlider(text, sliderValue, onSliderValueChange)
        } else {
            CreateRangedSlider(text, sliderValues, onSliderValuesChange)
        }
    }
}
