package com.project.ifoah.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.ifoah.navigation.SCREENS
import com.project.ifoah.viewmodels.auth.AuthViewModel
import com.project.ifoah.widgets.LoadingSpinner

@Composable
fun RegisterScreen(navController: NavController, viewModel: AuthViewModel) {

    //CheckSignedIn(viewModel = viewModel, navController = navController)

    val focus = LocalFocusManager.current

    Box(modifier = Modifier.fillMaxHeight()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally) {

            val usernameState = remember { mutableStateOf(TextFieldValue()) }
            val emailState = remember { mutableStateOf(TextFieldValue()) }
            val passState = remember { mutableStateOf(TextFieldValue()) }
            var passVisible = remember { mutableStateOf(false)}

            Text(text = "Register",
                Modifier.padding(8.dp),
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif
            )

            OutlinedTextField(
                value = usernameState.value,
                onValueChange = { usernameState.value = it },
                modifier = Modifier.padding(8.dp),
                label = { Text(text = "First Name") })
            OutlinedTextField(
                value = emailState.value,
                onValueChange = { emailState.value = it },
                modifier = Modifier.padding(8.dp),
                label = { Text(text = "Email") })
            OutlinedTextField(
                value = passState.value,
                onValueChange = { passState.value = it },
                modifier = Modifier.padding(8.dp),
                trailingIcon = {
                    if (passVisible.value) {
                        IconButton(onClick = { passVisible.value = false }) {

                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = "account",
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    } else {
                        IconButton(onClick = { passVisible.value = true }) {

                            Icon(
                                imageVector = Icons.Default.VisibilityOff,
                                contentDescription = "account",
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    }
                },
                label = { Text(text = "Password") })


            Button(onClick = {
                focus.clearFocus(force = true)
                viewModel.signUp(
                    usernameState.value.text,
                    emailState.value.text,
                    passState.value.text,
                    usernameState.value.text
                )
            },
                modifier = Modifier.padding(8.dp)) {
                Text(text = "Submit")
            }


            Text(text = "Are you already an User? Go To Login ->",
                color = Color.Blue,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navController.navigate(route = "${SCREENS.Login}")
                    }
            )
        }
        val isLoading = viewModel.isInProgress.value

        if (isLoading) {
            LoadingSpinner()
        }
    }
}