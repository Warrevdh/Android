package com.example.boredapp.ui.createActivity.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CreateSlider(
    text: String,
    sliderValue: Float,
    enabled: Boolean = false,
    onValueChange: (Float) -> Unit,
) {
    Column {
        Text(
            text = text + ": %.1f".format(sliderValue),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp),
            color = if (enabled) Color.Black else Color.Gray,
        )
        Slider(
            enabled = enabled,
            value = sliderValue,
            onValueChange = onValueChange,
            valueRange = 0f..1f,
            steps = 9,
            modifier = Modifier
                .padding(horizontal = 32.dp),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRangedSlider(
    text: String,
    sliderValue: ClosedFloatingPointRange<Float>,
    enabled: Boolean = false,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
) {
    Column {
        Text(
            text = "Min. " + text + ": %.1f".format(sliderValue.start),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp),
            color = if (enabled) Color.Black else Color.Gray,
        )
        Text(
            text = "Max. " + text + ": %.1f".format(sliderValue.endInclusive),
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            color = if (enabled) Color.Black else Color.Gray,
        )
        RangeSlider(
            enabled = enabled,
            value = sliderValue,
            onValueChange = onValueChange,
            valueRange = 0f..1f,
            steps = 9,
            modifier = Modifier
                .padding(horizontal = 32.dp),
        )
    }
}
