package com.example.projekt_zespolowy

import Connection
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projekt_zespolowy.backend.DataHelpers.DataHelperLogin
import com.example.projekt_zespolowy.backend.DataHelpers.DatabaseHelperCourses
import com.example.projekt_zespolowy.backend.DataHelpers.DatabaseHelperProfil
import com.example.projekt_zespolowy.screens.AddCourse
import com.example.projekt_zespolowy.screens.CourseDetails
import com.example.projekt_zespolowy.screens.HomeScreen
import com.example.projekt_zespolowy.screens.LoginScreen
import com.example.projekt_zespolowy.screens.MainScreen1
import com.example.projekt_zespolowy.screens.RegisterScreen
import com.example.projekt_zespolowy.screens.YourProfile
import com.example.projekt_zespolowy.ui.theme.Projekt_zespolowyTheme
import com.example.projekt_zespolowy.ui.theme.dataViewModel

//Deklaracje konektorów
val dbCouses: DatabaseHelperCourses = DatabaseHelperCourses()
val dbProfil: DatabaseHelperProfil = DatabaseHelperProfil()
val dataViewModel: dataViewModel = dataViewModel()
val dbLoggedProfile: DataHelperLogin = DataHelperLogin()

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //Wywołania połączeń
        dbCouses.connection = Connection()
        dbProfil.connection = Connection()
        dbLoggedProfile.connection = Connection()



        //Przypisanie danych do konektora
        dbCouses.getCourses()
        Log.i("MAIL", dataViewModel.specyfic_profil)



        super.onCreate(savedInstanceState)
        setContent {
            Projekt_zespolowyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val context = LocalContext.current

                    NavHost(navController = navController, startDestination = "home"){

                        composable("home") {
                            HomeScreen(context = context, onClick = {
                                navController.navigate(it)
                            })
                        }

                        // kolejne definicje tras nawigacji

                        composable("rejestracja"){
                            RegisterScreen(context = context, onClick = {
                                navController.navigate(it)
                            })
                        }
                        composable("logowanie"){
                            LoginScreen(context = context, onClick = {
                                navController.navigate(it)

                            })
                        }
                        composable("glownyEkran"){
                            MainScreen1(onClick = {
                                navController.navigate(it)
                            })
                        }
                        composable("detaleKursu"){
                            CourseDetails(context = context, onClick = {
                                navController.navigate(it)
                            })
                        }
                        composable("wyloguj") {
                            LoginScreen(context = context, onClick = {
                                navController.navigate(it)
                            })
                        }
                        composable("profil") {
                            YourProfile(onClick = {
                                navController.navigate(it)
                            })
                        }
                        composable("dodajKurs") {
                            AddCourse(onClick = {
                                navController.navigate(it)
                            })
                        }
                    }
                }
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
