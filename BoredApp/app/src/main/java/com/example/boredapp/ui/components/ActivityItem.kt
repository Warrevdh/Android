package com.example.boredapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.boredapp.model.Activity

@Composable
fun ActivityItem(
    activity: Activity,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    ElevatedCard(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { expanded = !expanded },
    ) {
        val enterTransition = fadeIn(animationSpec = tween(300))
        val exitTransition = fadeOut(animationSpec = tween(300))
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text(text = activity.activity, fontWeight = FontWeight.Bold)
            Text(text = "Type: ${activity.type}")

            AnimatedVisibility(
                visible = expanded,
                enter = enterTransition,
                exit = exitTransition,
            ) {
                Column {
                    Text(text = "Participants: ${activity.participants}")
                    Text(text = "Price: ${activity.price}")
                    Text(text = "Accessibility: ${activity.accessibility}")
                    if (activity.link.isNotEmpty()) {
                        Text(text = "Link: ${activity.link}")
                    }
                }
            }
        }
    }
}
