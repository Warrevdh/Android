package com.example.boredapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ActivityItem(
    activity: String,
    type: String, 
    participants: Int,
    price: Double,
    accessibility: Double,
    modifier: Modifier = Modifier,
    link: String = "",
) {
    Box(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.surfaceVariant,
        ).fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(text = "Activity: $activity", fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "Type: $type", fontSize = 14.sp, lineHeight = 20.sp)
            Text(text = "Participants: $participants", fontSize = 14.sp, lineHeight = 20.sp)
            Text(text = "Price: $price", fontSize = 14.sp, lineHeight = 20.sp)

            if (link.isNotEmpty()) {
                Text(
                    text = link,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,
                )
            }

            Text(text = "Accessibility: $accessibility", fontSize = 14.sp, lineHeight = 20.sp)
        }
    }
}
