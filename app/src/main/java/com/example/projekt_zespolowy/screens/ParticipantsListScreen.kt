package com.example.projekt_zespolowy.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.projekt_zespolowy.Location
import com.example.projekt_zespolowy.components.DrawerContent
import com.example.projekt_zespolowy.components.TopBar
import com.example.projekt_zespolowy.components.infoContainers
import com.example.projekt_zespolowy.dbCouses


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParticipantsListScreen(onClick: (String) -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentLocation by remember { mutableStateOf(Location.PARTICIPANTS) } // tutaj zmiana stanu ikony na wypełnioną

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                // funkcja zawiera zawartość wysuwanego elementu
                DrawerContent(currentLocation, onClick) // przekazanie danych do Drawera żeby zmienił ikonę
            }
        },
    ) {
        Scaffold()
        { contentPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                // Ta funkcja zawiera górny pasek z przyciskiem i chipsami
                TopBar(contentPadding, scope, drawerState, currentLocation, infoContainers, onClick)

                // Zawartość ekranu pod paskiem

                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(650.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = colorScheme.tertiaryContainer
                    )
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items( dbCouses.records.size) { index ->

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .background(
                                        colorScheme.surface,
                                        shape = MaterialTheme.shapes.medium
                                    )
                                    .border(
                                        1.dp,
                                        colorScheme.onSurface,
                                        shape = MaterialTheme.shapes.medium
                                    )

                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        //Nazwa kusu
                                        text = "${dbCouses.records[index].Course_name}",
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.Center,
                                    )
                                    // tutaj będzie ilość zapisanych uczestników

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ParticipantsListScreenPreview() {
    val navController = rememberNavController()
    ParticipantsListScreen(onClick = { navController.navigate(route = it) })
}
