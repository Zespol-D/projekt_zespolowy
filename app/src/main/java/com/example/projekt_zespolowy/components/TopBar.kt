package com.example.projekt_zespolowy.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.compose.rememberNavController
import com.example.projekt_zespolowy.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun EditProfileContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(text = "Twój profil", fontSize = 22.sp)
        Spacer(
            modifier = Modifier
            .width(15.dp))
        Button( modifier = Modifier
            .height(50.dp),
            onClick = { /* Obsługa przycisku profilowego */ }
        ) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .width(50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(
                    text = "Edytuj",
                    fontSize = 13.sp
                )
                Text(text = "Profil",
                    fontSize = 13.sp
                )
            }
        }
    }
}
@Composable
fun SaveProfileContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(text = "Twój profil", fontSize = 22.sp)
        Spacer(
            modifier = Modifier
                .width(15.dp))
        Button( modifier = Modifier
            .height(50.dp),
            onClick = { /* Obsługa przycisku profilowego */ }
        ) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .width(50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(
                    text = "Zapisz",
                    fontSize = 13.sp
                )
                Text(text = "zmiany",
                    fontSize = 13.sp
                )
            }
        }
    }
}
@Composable
fun ContainerInfoContent() {
    LazyRow() {
        items(infoContainers) { info ->
            InfoContainer(info) // pobieranie danych z InfoContainers
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
@Composable
fun AddCourseContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            modifier = Modifier
                .height(50.dp)
                .width(120.dp),
            onClick = { /* Obsługa przycisku profilowego */ }
        ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "")
                    Spacer(
                        modifier = Modifier
                        .width(4.dp)
                    )
                    Text(
                        text = "Dodaj",
                        textAlign = TextAlign.Left,
                        fontSize = 16.sp
                    )
                }
        }
    }
}
@Composable
fun Participants() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Ilość zapisanych uczestników",
            fontSize = 18.sp)
    }
}
@Composable
fun CourseDetailsContent() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .background(MaterialTheme.colorScheme.background)
        .padding(start = 16.dp, top = 8.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "// Nazwa kursu", fontSize = 20.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(contentPadding: PaddingValues,
           scope: CoroutineScope,
           drawerState: DrawerState,
           currentLocation : Location,
           infoContainers: List<ContainerInfo>,
           onClick: (String) -> Unit
) {
    // kod całej kompozycji
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .padding(7.dp)
                .fillMaxWidth()
                .height(80.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ExtendedFloatingActionButton(
                content = {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = ""
                    )
                },
                onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .zIndex(1f)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                // wywołanie zawartości TopBar
                when (currentLocation) {

                    Location.HOME -> {
                        ContainerInfoContent()
                    }
                    Location.ADD_COURSE -> {
                        AddCourseContent()
                    }
                    Location.COURSE_DETAILS -> {
                        CourseDetailsContent()
                    }
                    Location.PROFILE -> {
                        EditProfileContent()
                    }
                    Location.EDITABLE_PROFILE -> {
                        SaveProfileContent()
                    }
                    Location.SETTINGS -> {
                        // zawartość dla ekranu ustawień
                    }
                    Location.LOG_OUT -> {
                        // zawartość dla wylogowania
                    }
                    Location.PARTICIPANTS -> {
                        Participants()
                    }
                }
            }
        }
    }
}


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarPreview() {
    val navController = rememberNavController()
    TopBar(
        contentPadding = PaddingValues(),
        scope = rememberCoroutineScope(),
        drawerState = rememberDrawerState(DrawerValue.Closed),
        onClick = { },
        currentLocation = Location.PARTICIPANTS,
        infoContainers = emptyList(),
    )
}
