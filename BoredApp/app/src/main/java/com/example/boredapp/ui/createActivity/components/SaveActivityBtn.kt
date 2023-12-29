package com.example.boredapp.ui.createActivity.components

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.boredapp.ui.createActivity.GenerateActivityViewModel

/**
 * Composable function representing buttons for canceling and saving an activity.
 *
 * @param generateActivityViewModel ViewModel for handling the generation and saving of activities.
 */
@Composable
fun SaveActivityBtns(generateActivityViewModel: GenerateActivityViewModel) {
    // Retrieve the context from the composition
    val context = LocalContext.current

    // Row containing cancel and save buttons
    Row {
        // Cancel button
        Button(onClick = {
            generateActivityViewModel.resetActivity()
        }) {
            Text("Cancel")
        }

        // Spacer for additional space between buttons
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))

        // Save button
        Button(onClick = {
            generateActivityViewModel.saveActivity()
            // Display a toast message to indicate that the activity is saved
            Toast.makeText(context, "Activity saved", Toast.LENGTH_SHORT).show()
        }) {
            Text("Save")
        }
    }
}
