package com.example.projekt_zespolowy.screens

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.projekt_zespolowy.R
import com.example.projekt_zespolowy.dataViewModel
import com.example.projekt_zespolowy.dbLoggedProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


enum class TextFieldState {
    EMPTY,
    INVALID,
    VALID,
    NOTHING
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(context: Context, onClick: (String) -> Unit){

    var login by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("")}
    var passwordVisible : Boolean by remember { mutableStateOf(false)}
    var passwordFieldFilling by remember { mutableStateOf("Hasło")}
    var loginFieldFilling by remember { mutableStateOf("Login")}
    var passwordFieldState by remember { mutableStateOf(TextFieldState.NOTHING) }
    var loginFieldState by remember { mutableStateOf(TextFieldState.NOTHING) }
    var counter by remember { mutableIntStateOf(0) }
    // keyboard Controller
    val keyboardController = LocalSoftwareKeyboardController.current
    // wibracje
    val context = LocalContext.current
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
    val vibrationDuration = 10L
    val pattern1 = longArrayOf(0, 100, 100, 100) // podwójne brzęknięcie
    // pola od problemów z kontem
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp
            )
        ) {
            append("Nie pamiętasz hasła?")
        }
    }

    // chowanie klawiatury
    fun hideKeyboard(){
        when{
            passwordFieldState == TextFieldState.VALID && loginFieldState == TextFieldState.VALID -> {
                keyboardController?.hide()
            }
        }
    }

// walidacja danych
    fun validate(){
        if (login.isEmpty()){
            if(password.isEmpty()){
                loginFieldFilling = "Pole jest puste"
                loginFieldState = TextFieldState.EMPTY
                passwordFieldFilling = "Pole jest puste"
                passwordFieldState = TextFieldState.EMPTY
            }
            else {
                loginFieldFilling = "Pole jest puste"
                loginFieldState = TextFieldState.EMPTY
                passwordFieldFilling = "Wprowadź wszystkie dane"
                passwordFieldState = TextFieldState.EMPTY
            }
        }
        else
        {
           if (login == "1"){
                if(password.isEmpty()){
                    loginFieldFilling = "Wprowadź wszystkie dane"
                    loginFieldState = TextFieldState.EMPTY
                    passwordFieldFilling = "Pole jest puste"
                    passwordFieldState = TextFieldState.EMPTY
                }
                else{
                   if (password == "1"){
                       loginFieldFilling = "Dane są poprawne"
                       loginFieldState = TextFieldState.VALID
                       passwordFieldFilling = "Dane są poprawne"
                       passwordFieldState = TextFieldState.VALID
                   }
                    else{
                        loginFieldFilling = "Dane logowania nie są poprawne"
                        loginFieldState = TextFieldState.INVALID
                        passwordFieldFilling = "Dane logowania nie są poprawne"
                        passwordFieldState = TextFieldState.INVALID
                        vibrator?.vibrate(pattern1, -1)
                        counter ++
                    }
                }
           }
            else{
                loginFieldFilling = "Dane logowania nie są poprawne"
                loginFieldState = TextFieldState.INVALID
                passwordFieldFilling = "Dane logowania nie są poprawne"
                passwordFieldState = TextFieldState.INVALID
                vibrator?.vibrate(pattern1, -1)
                counter ++
            }
        }

    }

    fun chechInBase(Login : String, Password : String) : String{
        dbLoggedProfile.getProfil("SELECT Mail FROM Users WHERE Login ='$Login' AND Password = '$Password'")
        Log.i("checkInBase", "querry")
        var logged = dbLoggedProfile.getMail()
        if(logged == "false"){
            // nie zalogowano
            Log.i("checkInBase", "nie zalogowano")
        }
        // zalogowano
        Log.i("checkInBase", "zalogowano: $logged")
        return  logged
    }


// widoczna część ekranu
    Box(modifier = Modifier.fillMaxSize())
    {}
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .weight(0.3F),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Outlined.Lock, // kłódka
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .weight(1F),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                OutlinedTextField(
                    value = login,
                    onValueChange = {
                        login = it
                        if (it.isEmpty()) {
                            loginFieldFilling = "Login"
                            // Utwórz efekt wibracji
                            val vibrationEffect = VibrationEffect.createOneShot(vibrationDuration, VibrationEffect.DEFAULT_AMPLITUDE)
                            // Wywołaj wibrację
                            vibrator?.vibrate(vibrationEffect)
                            loginFieldState = TextFieldState.NOTHING
                        }
                    },
                    label = {Text(
                        text = loginFieldFilling,
                        color = when(loginFieldState) {
                            TextFieldState.EMPTY -> MaterialTheme.colorScheme.error
                            TextFieldState.INVALID -> MaterialTheme.colorScheme.onError
                            TextFieldState.VALID -> Color.Green
                            else -> MaterialTheme.colorScheme.secondary
                        })
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            validate()
                            hideKeyboard()

                            if(loginFieldState == TextFieldState.VALID && passwordFieldState == TextFieldState.VALID){
                                onClick("glownyEkran")
                            }
                        }
                    )
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        if (it.isEmpty()) {
                            passwordFieldFilling = "Hasło"
                            // Utwórz efekt wibracji
                            val vibrationEffect = VibrationEffect.createOneShot(vibrationDuration, VibrationEffect.DEFAULT_AMPLITUDE)
                            // Wywołaj wibrację
                            vibrator?.vibrate(vibrationEffect)
                            passwordFieldState = TextFieldState.NOTHING
                        }
                    },
                    label = {Text(
                        text = passwordFieldFilling,
                        color = when(passwordFieldState) {
                            TextFieldState.EMPTY -> MaterialTheme.colorScheme.error
                            TextFieldState.INVALID -> MaterialTheme.colorScheme.onError
                            TextFieldState.VALID -> Color.Green
                            else -> MaterialTheme.colorScheme.secondary
                        })
                    },
                    singleLine = true,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            validate()
                            hideKeyboard()
                            if(loginFieldState == TextFieldState.VALID && passwordFieldState == TextFieldState.VALID){
                                onClick("glownyEkran")
                            }
                        }),
                    trailingIcon = {
                        val imageVectorOn = painterResource(R.drawable.eye_off)
                        val imageVectorOff = painterResource(R.drawable.eye_on)
                        Crossfade(
                            targetState = passwordVisible,
                            animationSpec = tween(durationMillis = 400),
                            label = "CrossfadeLabel"
                        ) { targetVisibleState ->
                            val currentImageVector = if (targetVisibleState) imageVectorOn else imageVectorOff
                            Icon(
                                painter = currentImageVector,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable {
                                        passwordVisible = !passwordVisible
                                    },
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Button(modifier = Modifier
                    .width(width = 180.dp)
                    .height(height = 60.dp)
                    .padding(3.dp),
                    onClick = {
                        CoroutineScope(MainScope().coroutineContext).launch {
                            //validate()
                            //hideKeyboard()
                            var user = chechInBase(login, password)
                            dataViewModel.update_profil(user)
                            delay(200)
                            dataViewModel.update_profil(user)
                            onClick("glownyEkran")
                            if(loginFieldState == TextFieldState.VALID && passwordFieldState == TextFieldState.VALID && dataViewModel.specyfic_profil != "false"){
                                onClick("glownyEkran")
                            }
                        }
                    }) {
                    Text(text = "Zaloguj")
                }
            }
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .weight(0.7F),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                ClickableText(modifier = Modifier,
                    text = annotatedText,
                    onClick = {

                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview(){
    val navController = rememberNavController()
    val context = LocalContext.current // pobieramy aktualny kontekst
    LoginScreen(context= context, onClick = { navController.navigate(
        route = it) })
}
