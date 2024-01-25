package com.example.projekt_zespolowy.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.compose.rememberNavController
import com.example.projekt_zespolowy.Location
import com.example.projekt_zespolowy.components.DrawerContent
import com.example.projekt_zespolowy.components.TopBar
import com.example.projekt_zespolowy.components.infoContainers
import com.example.projekt_zespolowy.dataViewModel
import com.example.projekt_zespolowy.dbCouses

@Composable
fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen1(onClick: (String) -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentLocation by remember { mutableStateOf(Location.HOME) } // tutaj zmiana stanu ikony na wypełnioną

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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(6.dp),
                            text = "Lista kursów",
                            fontSize = 23.sp,
                            style = TextStyle(fontWeight = FontWeight.ExtraBold)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items( dbCouses.records.size) { index ->
                                val context = LocalContext.current // Pobieramy kontekst

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .clickable {

                                            //Co robi tan onclikc i czy może przekazywać więcej wartości niż ta jedna?
                                            onClick("detaleKursu")
                                            dataViewModel.updatePerson(dbCouses.records[index].Course_name)
                                            Toast
                                                .makeText(
                                                    context,
                                                    //Zamieniłem na nazwe kursu by łatwiej zobaczyć czy poprawnie zintegrowane
                                                    "Kliknięto element ${dbCouses.records[index].Course_name}",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        }
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
                                        //Dane kusu są w tablicy records !!

                                        Text(text = dbCouses.records[index].Start_date)
                                        Text(text = dbCouses.records[index].End_date)
                                        Text(text = dbCouses.records[index].Organizer_name)
                                        Text(text = dbCouses.records[index].Organizer_LastName)

                                    }
                                }
                            }
                        }
                        FloatingActionButton(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(16.dp)
                                .zIndex(1f), // Ustawienie wartości zIndex na 1, żeby ten chujowy przycisk był zawsze na wierzchu
                            onClick = {
                                onClick("listaUczestnikow")
                            }
                        ) {
                            Text(text = "Ilość uczestników")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreen1Preview() {
    val navController = rememberNavController()
    MainScreen1(onClick = { navController.navigate(route = it) })
}
