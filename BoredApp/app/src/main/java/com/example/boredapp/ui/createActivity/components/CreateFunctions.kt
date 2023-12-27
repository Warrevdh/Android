package com.example.boredapp.ui.createActivity.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateFunctions(
    checkedType: Boolean,
    checkedAccessibility: Boolean,
    checkedPrice: Boolean,
    checkedParticipants: Boolean,
    dropdownAccessibility: Boolean,
    dropdownPrice: Boolean,
    accesibilitySliderValue: Float,
    accesibilitySliderValues: ClosedFloatingPointRange<Float>,
    priceSlidesValue: Float,
    priceSlidesValues: ClosedFloatingPointRange<Float>,
    onDropdownCheckedChange: (Boolean) -> Unit,
    onSliderOneCheckedChange: (Boolean) -> Unit,
    openDropdownOne: () -> Unit,
    dismissDropdownOne: () -> Unit,
    onAccSliderValueChange: (Float) -> Unit,
    onAccSliderValuesChange: (ClosedFloatingPointRange<Float>) -> Unit,
    onSliderTwoCheckedChange: (Boolean) -> Unit,
    openDropdownTwo: () -> Unit,
    dimissDropdownTwo: () -> Unit,
    onPriceSliderValueChange: (Float) -> Unit,
    onPriceSliderValiesChange: (ClosedFloatingPointRange<Float>) -> Unit,
    onCheckedPartChange: (Boolean) -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Checkbox(
                checked = checkedType,
                onCheckedChange = onDropdownCheckedChange, 
            )
            Dropdown(checkedType)
        }
        Spacer(modifier = Modifier.height(20.dp))
        RowComponent(
            checked = checkedAccessibility,
            onCheckedChange = onSliderOneCheckedChange,
            openDropdown = openDropdownOne,
            dropdown = dropdownAccessibility,
            dismissDropdown = dismissDropdownOne,
            sliderValue = accesibilitySliderValue,
            sliderValues = accesibilitySliderValues,
            onSliderValueChange = onAccSliderValueChange,
            onSliderValuesChange = onAccSliderValuesChange,
            text = "Toegankelijkheid",
        )
        Spacer(modifier = Modifier.height(20.dp))
        RowComponent(
            checked = checkedPrice,
            onCheckedChange = onSliderTwoCheckedChange,
            openDropdown = openDropdownTwo,
            dropdown = dropdownPrice,
            dismissDropdown = dimissDropdownTwo,
            sliderValue = priceSlidesValue,
            sliderValues = priceSlidesValues,
            onSliderValueChange = onPriceSliderValueChange,
            onSliderValuesChange = onPriceSliderValiesChange,
            text = "Prijs",
        )
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = checkedParticipants, 
                onCheckedChange = onCheckedPartChange,
            )
            TextField(
                enabled = checkedParticipants && !checkedAccessibility && !checkedPrice && !checkedType,
                label = { Text(text = "Deelnemers:") },
                onValueChange = {},
                value = "",
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}

// @Preview
// @Composable
// fun Prev() {
//    CreateFunctions()
// }
