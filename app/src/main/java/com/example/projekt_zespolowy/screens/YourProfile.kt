package com.example.projekt_zespolowy.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.projekt_zespolowy.Location
import com.example.projekt_zespolowy.R
import com.example.projekt_zespolowy.components.DrawerContent
import com.example.projekt_zespolowy.components.TopBar
import com.example.projekt_zespolowy.components.infoContainers
import com.example.projekt_zespolowy.dataViewModel
import com.example.projekt_zespolowy.dbProfil
fun getProfilQuery(mail : String) : String{
    var newQuery = "SELECT dbo.Profil.* FROM dbo.Profil WHERE Mail = '$mail'"
    Log.i("newProfileQuery", newQuery)
    return newQuery
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YourProfile(onClick: (String) -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentLocation by remember { mutableStateOf(Location.PROFILE) } // tutaj zmiana stanu ikony na wypełnioną
    dbProfil.getProfil(getProfilQuery(dataViewModel.getPrifile()))

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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier
                                .height(25.dp)
                                .fillMaxWidth()
                            )
                            // Zdjęcie profilowe
                            Card(
                                shape = MaterialTheme.shapes.small,
                                elevation = 4.dp,
                                modifier = Modifier
                                    .size(130.dp)
                                    .padding(8.dp),
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.profileicon), // Podmień na własną ścieżkę do zdjęcia
                                    contentDescription = "Profile Picture",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }

                        Column(modifier = Modifier
                            .fillMaxSize()
                            .weight(3f),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier
                                .height(20.dp)
                                .fillMaxWidth()
                            )
                            Row {
                                Text(text = dbProfil.records.first().FirstName,
                                    fontSize = 30.sp,
                                    style = TextStyle(fontWeight = FontWeight.ExtraBold),
                                    textAlign = TextAlign.Center)
                                Spacer(modifier = Modifier
                                    .width(6.dp)
                                )
                                Text(text = dbProfil.records.first().LastName,
                                    fontSize = 30.sp,
                                    style = TextStyle(fontWeight = FontWeight.ExtraBold),
                                    textAlign = TextAlign.Center)
                            }
                            Text(
                                modifier = Modifier.padding(16.dp),
                                text = "Mentor",
                                fontSize = 20.sp,
                                style = TextStyle(fontWeight = FontWeight.Light),
                                textAlign = TextAlign.Center
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Inne elementy Row

                            Text(
                                text = "Ostatnio online: 21:37",
                                modifier = Modifier
                                    .weight(1f)  // Ustawia wagę, aby tekst zajmował dostępne miejsce
                                    .padding(end = 16.dp),  // Dodałem margines po prawej stronie
                                textAlign = TextAlign.End  // TextAlignment ustawiony na prawo
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun YourProfilePreview() {
    val navController = rememberNavController()
    YourProfile(onClick = { navController.navigate(route = it) })
}