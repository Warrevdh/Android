package com.example.boredapp.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Dialog(
    closeDialog: () -> Unit,
    onConfirm: () -> Unit,
    title: String,
    text: String,
) {
    AlertDialog(
        onDismissRequest = closeDialog,
        confirmButton = {
            IconButton(
                onClick = {
                    closeDialog()
                    onConfirm()
                },
            ) {
                Text(text = "Yes")
            }
        },
        dismissButton = {
            IconButton(
                onClick = closeDialog,
            ) {
                Text(text = "No")
            }
        },
        title = { Text(text = title) },
        text = { Text(text = text) },
    )
}
