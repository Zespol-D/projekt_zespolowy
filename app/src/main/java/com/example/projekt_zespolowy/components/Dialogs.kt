package com.example.projekt_zespolowy.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


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
@Composable
fun ChooseDate(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    onDateSelected: (Triple<Int, Int, Int>) -> Unit
) {
    var selectedDate: Triple<Int, Int, Int>? by remember { mutableStateOf(null) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                selectedDate?.let { onDateSelected(it) }
                onDismissRequest()
            },
            title = { Text(
                text = "Wybierz datę",
                style =  TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
                    },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Tutaj wywołaj kalendarz
                    ShowDatePicker { year, month, dayOfMonth ->
                        selectedDate = Triple(year, month, dayOfMonth)
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        selectedDate?.let { onDateSelected(it) }
                        onDismissRequest()
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "")
                    //Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "")
                    //Text("Anuluj")
                }
            }
        )
    }
}
@Preview
@Composable
fun ChooseDatePreview() {
    var showDialog by remember { mutableStateOf(true) }

    ChooseDate(
        showDialog = showDialog,
        onDismissRequest = {
            showDialog = false
        },
        onDateSelected = { selectedDate ->
            // Do something with the selected date
            println("Selected date: $selectedDate")
        }
    )
}
/*
@Composable
fun ChooseDate(
    showDialog: Boolean,
    //onConfirmation: (Int, Int, Int) -> Unit,
    onDismissRequest: () -> Unit,
    sharedViewModel: SharedViewModel = viewModel()
) {
    var selectedDate: Triple<Int, Int, Int>? by remember { mutableStateOf(null) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                // Jeżeli selectedDate nie jest null, to znaczy, że został naciśnięty przycisk "OK"
                selectedDate?.let { (year, month, dayOfMonth) ->
                    sharedViewModel.setDatePickerData(year, month, dayOfMonth)
                }
                onDismissRequest()
            },
            title = { Text("Wybierz datę") },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Tutaj wywołaj kalendarz
                    ShowDatePicker { year, month, dayOfMonth ->
                        selectedDate = Triple(year, month, dayOfMonth)
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Przypisanie wybranej daty i zamknięcie okna dialogowego
                        selectedDate?.let { (year, month, dayOfMonth) ->
                            sharedViewModel.setDatePickerData(year, month, dayOfMonth)
                        }
                        onDismissRequest()
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        // Zamknięcie okna dialogowego

                        onDismissRequest()
                    }
                ) {
                    Text("Anuluj")
                }
            }
        )
    }
}
*/