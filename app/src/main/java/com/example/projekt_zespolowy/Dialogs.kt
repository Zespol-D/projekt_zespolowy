package com.example.projekt_zespolowy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    onCancel: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Tak")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onCancel()
                }
            ) {
                Text("Nie")
            }
        }
    )
}

@Composable
fun LogOut(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismissRequest() },
            onConfirmation = {
                onDismissRequest()
                onConfirmation()
            },
            onCancel = {
                onDismissRequest()

            },
            dialogTitle = "Wylogowanie",
            dialogText = "Czy napewno chcesz się wylogować?",
            icon = Icons.Default.Info
        )
    }
}