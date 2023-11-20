package com.example.projekt_zespolowy.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun Confirmed() {
    var isAnimationComplete by remember { mutableStateOf(false) }
    var isConfirmed by remember { mutableStateOf(false) }

    // Rozpoczęcie efektu po wyrenderowaniu komponentu
    LaunchedEffect(true) {
        // Symulacja operacji asynchronicznej (np. wywołanie sieciowe)
        delay(2000) // Symulacja 2-sekundowego opóźnienia
        // Ustawienie stanu animacji i potwierdzenia po zakończeniu operacji
        isAnimationComplete = true
        isConfirmed = true
        delay(2000) // dodatkowe 2 sekundy po potwierdzeniu

        // navController.navigate("MainScreen1")
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Animacja ładowania
        if (!isAnimationComplete) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
                    .align(Alignment.Center)
            )
        }
        else if (isConfirmed) {
            Column(modifier = Modifier
                .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = Color.Green
                    )
                }
                Row(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Zapisano na kurs!",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Failed() {
    // Tu będzie obsługa sytuacji, gdy zapisanie na kurs się nie powiedzie
}

@Preview
@Composable
fun ConfirmedPreview1() {
    Confirmed()
}

