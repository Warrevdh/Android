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

/**
 * Composable function representing a slider for a single value.
 *
 * @param text The label or description associated with the slider.
 * @param sliderValue The current value of the slider.
 * @param onValueChange Callback function to be executed when the slider value changes.
 */
@Composable
fun CreateSlider(
    text: String,
    sliderValue: Float,
    onValueChange: (Float) -> Unit,
) {
    Column {
        // Display the current value of the slider
        Text(
            text = "$text: %.1f".format(sliderValue),
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 32.dp),
            Color.Black,
        )
        // Slider for selecting a single value
        Slider(
            value = sliderValue,
            onValueChange = onValueChange,
            valueRange = 0f..1f,
            steps = 9,
            modifier =
                Modifier
                    .padding(horizontal = 32.dp),
        )
    }
}

/**
 * Composable function representing a ranged slider with minimum and maximum values.
 *
 * @param text The label or description associated with the ranged slider.
 * @param sliderValue The current range of values selected on the slider.
 * @param onValueChange Callback function to be executed when the range slider values change.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRangedSlider(
    text: String,
    sliderValue: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
) {
    Column {
        // Display the minimum value of the range slider
        Text(
            text = "Min. $text: %.1f".format(sliderValue.start),
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 32.dp),
            color = Color.Black,
        )
        // Display the maximum value of the range slider
        Text(
            text = "Max. $text: %.1f".format(sliderValue.endInclusive),
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally),
            color = Color.Black,
        )
        // RangeSlider for selecting a range of values
        RangeSlider(
            value = sliderValue,
            onValueChange = onValueChange,
            valueRange = 0f..1f,
            steps = 9,
            modifier =
                Modifier
                    .padding(horizontal = 32.dp),
        )
    }
}
