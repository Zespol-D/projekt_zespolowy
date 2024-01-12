package com.example.projekt_zespolowy.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.compose.rememberNavController
enum class TextFieldStateRegistration {
    EMPTY,
    INVALID,
    VALID,
    NOTHING
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(context: Context, onClick: (String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("")}
    var nationality by remember { mutableStateOf("")}
    var postCode by remember { mutableStateOf("")}
    var password1 by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var nameOfOrganization by remember { mutableStateOf("") }
    // Tutaj przechowujemy wybrany element z wybieratora płci
    var gender by remember { mutableStateOf("") }
    var wybranyElementSize by remember { mutableStateOf(Size.Zero)}


    // wypełnienia pól (labels)
    var nameFieldFilling by remember { mutableStateOf("Imię")}
    var surnameFieldFilling by remember { mutableStateOf("Nazwisko")}
    var emailFieldFilling by remember { mutableStateOf("E-mail")}
    var phoneFieldFilling by remember { mutableStateOf("Telefon")}
    var nationalityFieldFilling by remember { mutableStateOf("narodowość")}
    var password1Filling by remember { mutableStateOf("Hasło") }
    var password2Filling by remember { mutableStateOf("Powtórz hasło")}
    var nameOfOrganizationFilling by remember { mutableStateOf("Nazwa organizacji")}
    var postCodeFilling by remember { mutableStateOf("Kod pocztowy")}


    // Expanded jest stanem otwartego/zamkniętego Dropdown Menu
    var mExpanded by remember { mutableStateOf(false) }

    // Lista płci
    val płcie = listOf("Kobieta", "Mężczyzna", "Inna")



    // podmiana ikon zależnie od sytuacji
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    // stany pól
    var passwordVisible : Boolean by remember { mutableStateOf(false)}
    var passwordFieldState by remember { mutableStateOf(TextFieldStateRegistration.NOTHING) }
    var organizationState by remember { mutableStateOf( false)}
    var isChecked by remember { mutableStateOf(false) }
    var isCheckedAgree by remember { mutableStateOf(false)}


    // keyboard Controller
    val keyboardController = LocalSoftwareKeyboardController.current



    fun isEmailValid(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return email.matches(emailRegex.toRegex())
    }

    fun validate() {
        when {
            (password1 != password2) -> {
                password1Filling = "Hasła nie są takie same"
                password2Filling = "Hasła nie są takie same"
            }
            !isEmailValid(email) -> {
                emailFieldFilling = "Nieprawidłowy adres e-mail"
            }
        }
    }
    fun isOrganization(){
        organizationState = true
    }
    fun hideKeyboard(){
        when{
            name.isNotEmpty() && surname.isNotEmpty() && email.isNotEmpty() && password1 == password2 && isCheckedAgree -> {
                keyboardController?.hide()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Rejestracja konta",
                fontSize = 27.sp,
                color = MaterialTheme.colorScheme.onSurface)
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = MaterialTheme.colorScheme.tertiaryContainer)
        )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )
                    OutlinedTextField(modifier = Modifier
                        .width(250.dp)
                        .height(75.dp),
                        value = name,
                        onValueChange = {
                            name = it
                            if (it.isEmpty()) {
                                nameFieldFilling = "Imię"
                            }
                        },
                        label = {
                            Text(text = "Imię")
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
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )
                }
                item {
                    OutlinedTextField(modifier = Modifier
                        .width(250.dp)
                        .height(75.dp),
                        value = surname,
                        onValueChange = {
                            surname = it
                            if (it.isEmpty()) {
                                surnameFieldFilling = "Nazwisko"

                            }
                        },
                        label = {
                            Text(text = "Nazwisko")
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
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )
                }
                item {
                    OutlinedTextField(modifier = Modifier
                        .width(250.dp)
                        .height(75.dp),
                        value = email,
                        onValueChange = {
                            email = it
                            if (it.isEmpty()) {
                                emailFieldFilling = "E-mail"
                            }
                        },
                        label = {
                            Text(text = "E-mail")
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
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )
                }
                item {
                    OutlinedTextField(
                        modifier = Modifier
                            .width(250.dp)
                            .height(75.dp),
                        value = phone,
                        onValueChange = {
                            phone = it
                            if (it.isEmpty()) {
                                phoneFieldFilling = "Telefon"
                            }
                        },
                        label = {
                            Text(text = "Telefon")
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
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )
                }
                item {
                    Column(
                        modifier = Modifier
                            .width(250.dp)
                            .height(75.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = gender,
                            onValueChange = { gender = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    // Ta wartość jest używana do przypisania
                                    // szerokości Dropdown Menu do szerokości kolumny
                                    wybranyElementSize = coordinates.size.toSize()
                                },
                            label = {Text("Płeć")},
                            readOnly = true,
                            trailingIcon = {
                                Icon(icon,"contentDescription",
                                    Modifier.clickable { mExpanded = !mExpanded })
                            }
                        )

                        // Tutaj jest tworzone te Dropdown Menu
                        DropdownMenu(
                            expanded = mExpanded,
                            onDismissRequest = { mExpanded = false },
                            modifier = Modifier
                                .width(with(LocalDensity.current){wybranyElementSize.width.toDp()})
                        ) {
                            płcie.forEach { label ->
                                DropdownMenuItem(onClick = {
                                    gender = label
                                    mExpanded = false
                                }) {
                                    Text(text = label)
                                }
                            }
                        }
                    }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                        )
                }
                item {
                    OutlinedTextField(modifier = Modifier
                        .width(250.dp)
                        .height(75.dp),
                        value = nationality,
                        onValueChange = {
                            nationality = it
                            if (it.isEmpty()) {
                                nationalityFieldFilling = "Narodowość"
                            }
                        },
                        label = {
                            Text(text = "Narodowość")
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
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )
                }
                item {
                    OutlinedTextField(modifier = Modifier
                        .width(250.dp)
                        .height(75.dp),
                        value = postCode,
                        onValueChange = {
                            postCode= it
                            if (it.isEmpty()) {
                                postCodeFilling = "Kod pocztowy"
                            }
                        },
                        label = {
                            Text(text = "Kod pocztowy")
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
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )
                }
                // kod pocztowy
                item {
                    OutlinedTextField(modifier = Modifier
                        .width(250.dp)
                        .height(75.dp),
                        value = password1,
                        onValueChange = {
                            password1 = it
                            if (it.isEmpty()) {
                                password1Filling = "Hasło"
                            }
                        },
                        label = {
                            Text(password1Filling)
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                hideKeyboard()
                            }
                        )
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )
                }
                item {
                    OutlinedTextField(modifier = Modifier
                        .width(250.dp)
                        .height(75.dp),
                        value = password2,
                        onValueChange = {
                            password2 = it
                            if (it.isEmpty()) {
                                password1Filling = "Powtórz hasło"
                            }
                        },
                        label = {
                            Text(text = password2Filling)
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                hideKeyboard()
                            }
                        )
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = {
                                isChecked = it
                                if (!organizationState) {
                                    isOrganization()
                                } else {
                                    organizationState = false
                                }

                            },
                            modifier = Modifier
                                .padding(start = 53.dp)
                        )
                        Text(
                            color = MaterialTheme.colorScheme.surfaceTint, fontSize = 15.sp,
                            text = "Jestem organizacją"
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(3.dp)
                        )
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        OutlinedTextField(modifier = Modifier
                            .width(250.dp)
                            .height(75.dp),
                            value = nameOfOrganization,
                            onValueChange = {
                                nameOfOrganization = it
                                if (it.isEmpty()) {
                                    nameOfOrganizationFilling = "Nazwa organizacji"
                                }
                            },
                            enabled = organizationState,
                            label = {
                                Text(text = "Nazwa organizacji")
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
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Checkbox(
                            checked = isCheckedAgree,
                            onCheckedChange = { isCheckedAgree = it },
                            modifier = Modifier
                                .padding(start = 53.dp)
                        )
                        Text(
                            color = MaterialTheme.colorScheme.surfaceTint, fontSize = 15.sp,
                            text = "Zgoda na przetwarzanie danych"
                        )
                    }
                }
            }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = MaterialTheme.colorScheme.tertiaryContainer)
        )
        Row(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(modifier = Modifier
                .width(width = 180.dp)
                .height(height = 60.dp)
                .padding(3.dp),onClick = {
            }
            ) {
                Text(text = "Załóż konto")
            }
        }
    }
}


@Preview
@Composable
fun RegisterScreenPreview() {
    val navController = rememberNavController()
    val context = LocalContext.current // pobieramy aktualny kontekst
    RegisterScreen(context = context, onClick = {
        navController.navigate(
            route = it
        )
    })
}