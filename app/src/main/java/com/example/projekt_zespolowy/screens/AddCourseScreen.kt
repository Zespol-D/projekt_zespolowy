package com.example.projekt_zespolowy.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.projekt_zespolowy.Location
import com.example.projekt_zespolowy.components.DrawerContent
import com.example.projekt_zespolowy.components.TopBar
import com.example.projekt_zespolowy.components.infoContainers
import com.example.projekt_zespolowy.components.showDatePicker


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCourse(onClick: (String) -> Unit) {

    // zmienne obsługujące pola tekstowe
    var name by remember { mutableStateOf("") }
    var nameFieldFilling by remember { mutableStateOf("")}
    var price by remember {mutableStateOf("")}
    var priceFieldFilling by remember { mutableStateOf("")}

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentLocation by remember { mutableStateOf(Location.ADD_COURSE) } // tutaj zmiana stanu ikony na wypełnioną

    // keyboard controller
    val keyboardController = LocalSoftwareKeyboardController.current


    fun hideKeyboard(){
        when{
            name.isNotEmpty() -> {
                keyboardController?.hide()
            }
        }
    }

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
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    )
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
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
                                text = "Dodawanie kursu",
                                fontSize = 23.sp,
                                style = TextStyle(fontWeight = FontWeight.ExtraBold)
                            )
                        }
                        OutlinedTextField(modifier = Modifier
                            .width(250.dp)
                            .height(75.dp),
                            value = name,
                            onValueChange = {
                                name = it
                                if (it.isEmpty()) {
                                    nameFieldFilling = "Organizator"
                                }
                            },
                            label = {
                                Text(text = "Organizator")
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    hideKeyboard()
                                }
                            )
                        )
                        OutlinedTextField(modifier = Modifier
                            .width(250.dp)
                            .height(75.dp),
                            value = price,
                            onValueChange = {
                                price = it
                                if (it.isEmpty()) {
                                    priceFieldFilling = "Cena za udział"
                                }
                            },
                            label = {
                                Text(text = "Cena za udział")
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    hideKeyboard()
                                }
                            )
                        )
                        showDatePicker()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AddCoursePreview() {
    val navController = rememberNavController()
    AddCourse(onClick = { navController.navigate(route = it) })
}