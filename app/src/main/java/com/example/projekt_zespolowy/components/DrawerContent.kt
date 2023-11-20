package com.example.projekt_zespolowy.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.projekt_zespolowy.Location


// to jest zawartość wysuwanego elementu od boku
@Composable
fun DrawerContent(currentLocation: Location, onClick: (String) -> Unit) {
    val (showLogoutDialog, setShowLogoutDialog) = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .width(225.dp)
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(200.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Menu", fontSize = 32.sp)
            }
            FilledTonalButton(
                modifier = Modifier
                    .width(182.dp),
                onClick = {

                    onClick("glownyEkran")
                }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = if (currentLocation == Location.HOME) Icons.Filled.Home else Icons.Outlined.Home, contentDescription = "")
                    Text(
                        text = "  Wszystkie kursy",
                        textAlign = TextAlign.Start
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(4.dp)
                    .fillMaxWidth()
            )

            FilledTonalButton(
                modifier = Modifier
                    .width(182.dp),
                onClick = {
                    onClick("dodajKurs")
                }) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = if (currentLocation == Location.ADD_COURSE) Icons.Filled.Add else Icons.Outlined.Add, contentDescription = "")
                    Text(
                        text = "  Dodaj kurs",
                        textAlign = TextAlign.Left
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(4.dp)
                    .fillMaxWidth()
            )

            FilledTonalButton(
                modifier = Modifier
                    .width(182.dp),
                onClick = {
                    onClick("profil")
                }) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (currentLocation == Location.PROFILE) Icons.Filled.AccountCircle else Icons.Outlined.AccountCircle,
                        contentDescription = ""
                    )
                    Text(
                        text = "  Profil",
                        textAlign = TextAlign.Left
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(4.dp)
                    .fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                FilledTonalButton(
                    modifier = Modifier
                        .width(182.dp),
                    onClick = {
                        setShowLogoutDialog(true) // zmienną stanu wyświetlany jest dialog
                    }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = ""
                        )
                        Text(
                            text = "  Wyloguj",
                            textAlign = TextAlign.Left
                        )
                    }
                }
                LogOut(
                    showDialog = showLogoutDialog,
                    onDismissRequest = { setShowLogoutDialog(false) },
                    onConfirmation = {
                        // Tutaj jest logika obsługi potwierdzenia
                        onClick("wyloguj")
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                ) {

                }
            }
        }
    }
}

@Preview
@Composable
fun DrawerContentPreview() {
    val navController = rememberNavController()

    // Tutaj ustawiana jest lokalizacja, Location.HOME
    val currentLocation = Location.PROFILE

    DrawerContent(currentLocation, onClick = { navController.navigate(route = it) })
}