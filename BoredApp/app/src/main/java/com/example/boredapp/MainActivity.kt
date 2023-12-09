package com.example.boredapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boredapp.ui.theme.BoredAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoredAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    ScaffoldExample()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var createNewActivity by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Top app bar")
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            if (createNewActivity) {
                CreateActivityItem(onCancel = {
                    createNewActivity = false
                })
            } else {
                ActivityList()
            }
        }
    }
}

@Preview
@Composable
fun ScaffoldExamplePreview() {
    BoredAppTheme {
        ScaffoldExample()
    }
}

@Composable
fun ActivityItem() {
    Box(
        modifier = Modifier.background(
            color = MaterialTheme.colorScheme.surfaceVariant,
        ).fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Text(text = "Title", fontSize = 16.sp, lineHeight = 24.sp)
                Text(text = "Subtitle\nSecond line", fontSize = 14.sp, lineHeight = 20.sp)
            }
            Checkbox(checked = true, onCheckedChange = {})
        }
    }
}

@Composable
fun CreateSlider(text: String) {
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
fun CreateRangedSlider(text: String) {
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
fun ActivityList() {
    Column(modifier = Modifier.fillMaxWidth()) {
        ActivityItem()
        ActivityItem()
    }
}

@Preview
@Composable
fun TaskListPreview() {
    BoredAppTheme {
        ActivityList()
    }
}

@Preview
@Composable
fun ActivityPreview() {
    BoredAppTheme {
        ActivityItem()
    }
}
