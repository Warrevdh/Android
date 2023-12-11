package com.example.boredapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxWidth()) {
        ActivityItem()
        ActivityItem()
    }
}
