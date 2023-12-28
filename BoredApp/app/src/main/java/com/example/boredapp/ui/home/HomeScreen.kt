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

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Column(
            modifier = modifier.fillMaxWidth(), 
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Bored app", 
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
            )
            Text(text = "Find something to do", fontSize = 16.sp)
        }
    }
}
