package com.example.boredapp.ui.components

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.boredapp.model.Activity

@Composable
fun ActivityItem(
    activity: Activity,
    modifier: Modifier = Modifier,
    inList: Boolean = false,
    showConfirmation: () -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    ElevatedCard(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable { expanded = !expanded },
    ) {
        val enterTransition = fadeIn(animationSpec = tween(300))
        val exitTransition = fadeOut(animationSpec = tween(300))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .weight(1f)
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
                            Text(
                                modifier = Modifier.clickable {
                                    val openUrl = Intent(Intent.ACTION_VIEW)
                                    openUrl.data = Uri.parse(activity.link)
                                    ContextCompat.startActivity(context, openUrl, Bundle())
                                },
                                color = Color.Blue,
                                textDecoration = TextDecoration.Underline,
                                text = activity.link,
                            )
                        }
                    }
                }
            }
            if (inList) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.Red,
                        ),
                        onClick = {
                            showConfirmation()
                        },
                    ) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Save",
                        )
                    }
                }
            }
        }
    }
}
