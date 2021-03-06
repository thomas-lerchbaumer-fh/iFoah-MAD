package com.project.ifoah.screens.auth


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.ifoah.navigation.SCREENS
import com.project.ifoah.viewmodels.auth.AuthViewModel
import com.project.ifoah.widgets.LoadingSpinner

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {

    val focus = LocalFocusManager.current


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .wrapContentHeight()
                .verticalScroll(
                    rememberScrollState()
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val emailState = remember { mutableStateOf(TextFieldValue()) }
            val passState = remember { mutableStateOf(TextFieldValue()) }
            var passVisible = remember { mutableStateOf(false) }

            Text(
                text = "iFoah",
                modifier = Modifier.padding(8.dp),
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif
            )
            OutlinedTextField(
                value = emailState.value,
                onValueChange = { emailState.value = it },
                modifier = Modifier.padding(8.dp),
                label = { Text(text = "Email") })
            OutlinedTextField(
                value = passState.value,
                onValueChange = { passState.value = it },
                modifier = Modifier.padding(8.dp),
                visualTransformation = if (passVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                label = { Text(text = "Password") },
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
                }

            )
            Button(
                onClick = {
                    authViewModel.login(emailState.value.text, passState.value.text)
                    focus.clearFocus(force = true)
                    if (authViewModel.singIn.value) navController.navigate(route = "${SCREENS.Home}")
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Login")
            }
            Text(text = "New Here? Go To Sign Up ->",
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navController.navigate(route = "${SCREENS.Register}")
                    }
            )
        }

        val isLoading = authViewModel.isInProgress.value
        if (isLoading) {
            LoadingSpinner()
        }
    }
}

