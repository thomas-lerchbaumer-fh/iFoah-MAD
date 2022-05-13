package com.project.ifoah.navigation

import android.util.Log
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.project.ifoah.R
import com.project.ifoah.screens.auth.LoginScreen
import com.project.ifoah.screens.auth.RegisterScreen
import com.project.ifoah.screens.helloTest
import com.project.ifoah.viewmodels.auth.AuthViewModel


@Composable
fun Navigation(){


    //init nav controller
    val navController = rememberNavController()

    //init view model
    val authViewModel = AuthViewModel(FirebaseAuth.getInstance(), FirebaseFirestore.getInstance());

    if(authViewModel.singIn.value){
            /* Add code later */
        val stuff = authViewModel
        Log.d("myuserdata", stuff.userData.toString())
        NavHost(navController = navController, startDestination = "${SCREENS.Home}"){
            composable("${SCREENS.Login}"){ LoginScreen(navController = navController, authViewModel = authViewModel)}
            composable("${SCREENS.Register}"){RegisterScreen(navController, viewModel = authViewModel)}
            composable("${SCREENS.Home}"){ helloTest(navController = navController,authViewModel = authViewModel) }

        }
    }else {
        NavHost(navController = navController, startDestination = "${SCREENS.Login}") {
            composable("${SCREENS.Login}") {
                LoginScreen(
                    navController = navController,
                    authViewModel = authViewModel
                )
            }
            composable("${SCREENS.Register}") {
                RegisterScreen(
                    navController,
                    viewModel = authViewModel
                )
            }
            composable("${SCREENS.Home}") {
                helloTest(
                    navController = navController,
                    authViewModel = authViewModel
                )
            }

        }
    }

}