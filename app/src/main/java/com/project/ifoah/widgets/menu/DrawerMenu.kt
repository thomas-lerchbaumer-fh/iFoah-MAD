package com.project.ifoah.widgets.menu

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.ifoah.R
import com.project.ifoah.navigation.SCREENS
import com.project.ifoah.viewmodels.auth.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun DrawerMenu(
    navController: NavController,
    authViewModel: AuthViewModel,
    title: String,
    content: @Composable () -> Unit = {}
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val userName = authViewModel.userData.value?.name;
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "menu")
                    }
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                    Text(text = title, style = MaterialTheme.typography.h6)
                }
            }
        },
        drawerBackgroundColor = MaterialTheme.colors.background,
        // scrimColor = Color.Red,  // Color for the fade background when you open/close the drawer
        drawerContent = {
            /* Add code later */
            menuHeader(userName = userName)
            Divider(
                modifier = Modifier.padding(top= 20.dp, bottom = 20.dp)
            )
            menuItems(authViewModel = authViewModel, navController = navController)
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
            ) {
                content()
            }
        }
    )
}

@Composable
fun menuHeader(userName: String?) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle, contentDescription = "account",
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colors.primary
        )
        Text(text = "Welcome, $userName", style = MaterialTheme.typography.body2)
    }

}


@Composable
fun menuItems(authViewModel: AuthViewModel, navController: NavController) {

    Column() {
        Row() {
            Icon(
                imageVector = Icons.Default.Home, contentDescription = "account",
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colors.primary
            )

            Text(text = "Home", style = MaterialTheme.typography.body2)
        }
    }

    Column(
        modifier = Modifier.clickable(
            onClick = {
                navController.navigate(route = "${SCREENS.Login}")
                authViewModel.signOut()


            }
        )
    ) {
        Row(
        ) {
            Icon(
                imageVector = Icons.Default.Logout, contentDescription = "account",
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colors.primary
            )
            Text(text = "Logout", style = MaterialTheme.typography.body2)
        }
    }





}


