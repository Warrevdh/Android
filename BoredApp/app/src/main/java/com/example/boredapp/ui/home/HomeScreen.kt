package com.example.boredapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Composable function for the home screen of the Bored app.
 *
 * @param modifier The [Modifier] for customizing the layout of the home screen.
 */
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // Box is used as the outer container to provide flexibility in layout
    Box(modifier = modifier) {
        // Column for vertically arranging text elements
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Large, bold title for the app
            Text(
                text = "Bored app",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
            )
            // Subtitle encouraging users to find something to do
            Text(text = "Find something to do", fontSize = 16.sp)
        }
    }
}
