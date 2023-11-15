package com.example.projekt_zespolowy.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(context: Context, onClick: (String) -> Unit){
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(modifier = Modifier
            .fillMaxSize()
            .weight(1F),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(color = MaterialTheme.colorScheme.tertiary,
                text = "NurturGuide",
                fontSize = 25.sp)
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .weight(1F),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(modifier = Modifier
                .width(width = 180.dp)
                .height(height = 60.dp)
                .padding(4.dp),
                onClick = {
                    onClick("rejestracja")
                }
            ) {
                Text(text = "Rejestracja", fontSize = 15.sp)
            }

        }

        Column(modifier = Modifier
            .fillMaxSize()
            .weight(1F)
            .padding(7.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(modifier = Modifier
                .width(width = 180.dp)
                .height(height = 60.dp)
                .padding(4.dp),
                onClick = {
                    onClick("logowanie")
                }
            ) {
                Text(text = "Logowanie", fontSize = 15.sp)
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    val navController = rememberNavController()
    val context = LocalContext.current // pobieramy aktualny kontekst
    HomeScreen(context= context, onClick = { navController.navigate(
        route = it) })
}