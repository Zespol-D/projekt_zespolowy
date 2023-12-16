package com.example.projekt_zespolowy.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.projekt_zespolowy.Location
import com.example.projekt_zespolowy.backend.dataclasses.Courses
import com.example.projekt_zespolowy.components.Confirmed
import com.example.projekt_zespolowy.components.DrawerContent
import com.example.projekt_zespolowy.components.TopBar
import com.example.projekt_zespolowy.components.infoContainers

@OptIn(ExperimentalMaterial3Api::class)
@Composable

// Tutaj do CourseDetails chciałbym przekazać Course_name pod nazwą Inner_Course_name
fun CourseDetails(onClick: (String) -> Unit, context: Context) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentLocation by remember { mutableStateOf(Location.COURSE_DETAILS) } // tutaj zmiana stanu ikony na wypełnioną
    // MutableState to manage the confirmation state
    var isConfirmed by remember { mutableStateOf(false) }
    var isClicked by remember { mutableStateOf(false) }


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

                // Tutaj na podstawie dbCourses.records[]
                /*

                for(item: Courses in dbCourses.records)
                {
                    for(nazwa : String in item.Course_name
                    if(Inner_Course_name == nazwa)
                    {
                    //wyświetlanie danych tutaj, lub przypisanie ich od nowej tablicy typu Courses
                    }
                }



                 */

                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(650.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    )
                ) {
                    if (isConfirmed) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(550.dp)
                                .padding(10.dp)
                                .background(
                                    MaterialTheme.colorScheme.surface,
                                    shape = MaterialTheme.shapes.medium
                                )
                                .border(
                                    1.dp,
                                    MaterialTheme.colorScheme.onSurface,
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
                                    text = "",
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center,
                                )
                                Confirmed()
                            }
                        }

                    } else {
                        // Zawartość ekranu pod paskiem
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(550.dp)
                                .padding(10.dp)
                                .background(
                                    MaterialTheme.colorScheme.surface,
                                    shape = MaterialTheme.shapes.medium
                                )
                                .border(
                                    1.dp,
                                    MaterialTheme.colorScheme.onSurface,
                                    shape = MaterialTheme.shapes.medium
                                )
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(text = "Organizator:",
                                    modifier = Modifier
                                        .padding(start = 16.dp, bottom = 10.dp),
                                    textAlign = TextAlign.Start,
                                    fontSize = 15.sp
                                )
                                Text(text = "Data rozpoczęcia:",
                                    modifier = Modifier
                                        .padding(start = 16.dp, bottom = 10.dp),
                                    textAlign = TextAlign.Start,
                                    fontSize = 15.sp
                                )
                                Text(text = "Data zakończenia:",
                                    modifier = Modifier
                                        .padding(start = 16.dp, bottom = 10.dp),
                                    textAlign = TextAlign.Start,
                                    fontSize = 15.sp
                                )

                                Text(text = "Cena zaudział:",
                                    modifier = Modifier
                                        .padding(start = 16.dp, bottom = 10.dp),
                                    textAlign = TextAlign.Start,
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            modifier = Modifier
                            .width(width = 120.dp)
                            .height(height = 50.dp),
                            onClick = {
                                onClick("glownyEkran")
                            }
                        ) {
                            Text(text = "Zamknij")
                        }
                        Spacer(modifier = Modifier
                            .fillMaxHeight()
                            .width(80.dp)
                        )
                        OutlinedButton( modifier = Modifier
                            .width(width = 120.dp)
                            .height(height = 50.dp),
                            onClick = {
                                if (!isClicked) {
                                    isConfirmed = true
                                }
                                else {
                                    Toast
                                        .makeText(
                                            context,
                                            "Już zapisano na kurs",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                }
                                isClicked = true
                            }
                        ) {
                            Text(text = "Zapisz się")

                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CourseDetailsPreview() {
    val navController = rememberNavController()
    val context = LocalContext.current
    CourseDetails(onClick = { navController.navigate(route = it) }, context = context)
}