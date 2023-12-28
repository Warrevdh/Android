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

@Composable
fun SaveActivityBtns(generateActivityViewModel: GenerateActivityViewModel) {
    val context = LocalContext.current
    
    Row {
        Button(onClick = {
            generateActivityViewModel.resetActivity()
        }) {
            Text("Annuleer")
        }
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Button(onClick = {
            generateActivityViewModel.saveActivity()
            Toast.makeText(context, "Activiteit opgeslaan", Toast.LENGTH_SHORT).show()
        }) {
            Text("Opslaan")
        }
    }
}
