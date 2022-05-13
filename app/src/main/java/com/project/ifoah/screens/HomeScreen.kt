package com.project.ifoah.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.project.ifoah.viewmodels.auth.AuthViewModel
import com.project.ifoah.widgets.TopBar
import com.project.ifoah.widgets.menu.DrawerMenu

@Composable
fun helloTest(navController: NavController,authViewModel: AuthViewModel){
    val name = authViewModel.userData.value

   DrawerMenu(navController = navController, authViewModel = authViewModel, title = "test"){
       hola(name?.name)
   }


}

@Composable
fun hola(name: String?){
    Text(text = "hello ${name}")
}