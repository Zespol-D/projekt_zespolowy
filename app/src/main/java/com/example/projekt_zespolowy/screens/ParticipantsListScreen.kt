package com.example.projekt_zespolowy.screens

import android.util.Log
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
import com.example.projekt_zespolowy.backend.dataclasses.Courses
import com.example.projekt_zespolowy.backend.dataclasses.Users
import com.example.projekt_zespolowy.components.DrawerContent
import com.example.projekt_zespolowy.components.TopBar
import com.example.projekt_zespolowy.components.infoContainers
import com.example.projekt_zespolowy.dataViewModel
import com.example.projekt_zespolowy.dbCouses
import com.example.projekt_zespolowy.dbUserCourse
import com.example.projekt_zespolowy.dbUsers


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParticipantsListScreen(onClick: (String) -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentLocation by remember { mutableStateOf(Location.PARTICIPANTS) } // tutaj zmiana stanu ikony na wypełnioną


    var coursesPerUser : MutableList<Courses>
    Log.d("specyficUSerId", dataViewModel.specyfic_user_id.toString())
    coursesPerUser = dataViewModel.GetCouses(dbCouses.records, 1)
    Log.d("IleKursów", coursesPerUser.size.toString() )
    Log.d("Jaki kurs" , dataViewModel.actural_course.toString())
    var Users : MutableList<Int>
    Users = dataViewModel.GetUsersForCourse(dbUserCourse.records, dataViewModel.actural_course)
    Log.d("Ile Userów", Users.size.toString())
    var Lastly : MutableList<Users> = dataViewModel.GetUsersById(dbUsers.records, Users)
    Log.d("Ile Lastly", Lastly.size.toString())
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
                ) {LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Header or other items can be added here before the main list
                    items(coursesPerUser.size) { index ->
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
                                    text = "${coursesPerUser[index].Course_name}",
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center,
                                )

                                // Other content for each item
                                this@LazyColumn.items(Lastly.size) { innerIndex ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center


                                    ) {
                                        Text(
                                            text = "${Lastly[innerIndex].FirstName}",
                                            fontSize = 14.sp,
                                            textAlign = TextAlign.Center,
                                        )
                                        Text(
                                            text = " ",
                                            fontSize = 14.sp,
                                            textAlign = TextAlign.Center,
                                        )
                                        Text(
                                            text = "${Lastly[innerIndex].LastName}",
                                            fontSize = 14.sp,
                                            textAlign = TextAlign.Center,
                                        )
                                    }
                                }
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
