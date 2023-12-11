package com.example.boredapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateActivityItem(onCancel: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CreateSlider("Toegankelijkheid")
        CreateSlider("Prijs")
        CreateRangedSlider("Toegankelijkheid")
        CreateRangedSlider("Prijs")
        TextField(
            label = { Text(text = "Deelnemers:") },
            onValueChange = {},
            value = "",
            modifier = Modifier.padding(8.dp),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(onClick = onCancel) {
                Text(text = "Annuleer")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Genereer")
            }
        }
    }
}

@Composable
private fun CreateSlider(text: String) {
    var sliderValue by remember { mutableStateOf(0.0f) }
    Column {
        Text(
            text = text + ": %.1f".format(sliderValue),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Slider(
            value = sliderValue,
            onValueChange = {
                sliderValue = it
            },
            valueRange = 0f..1f,
            steps = 9,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
        )
    }
}

@Composable
private fun CreateRangedSlider(text: String) {
    var sliderValue by remember { mutableStateOf(0.0f..1.0f) }
    Column {
        Text(
            text = "Min. " + text + ": %.1f".format(sliderValue.start),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = "Max. " + text + ": %.1f".format(sliderValue.endInclusive),
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        RangeSlider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..1f,
            steps = 9,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
        )
    }
}
