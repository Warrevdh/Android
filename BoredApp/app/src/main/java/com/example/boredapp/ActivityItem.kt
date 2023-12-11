package com.example.boredapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
