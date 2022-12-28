package com.pa.dailychallengecards.presentation.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun SelectionDialog(
    onDismiss : () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss },
        confirmButton = {
            TextButton(onClick = {}) { Text(text = "OK") }
        },
        dismissButton = {
            TextButton(onClick = {}) { Text(text = "Cancel") }
        },
        title = { Text(text = "Please confirm") },
        text = { Text(text = "Should I continue with the requested action?") }
    )
}