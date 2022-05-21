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
import com.project.ifoah.screens.HomeScreen
import com.project.ifoah.screens.auth.LoginScreen
import com.project.ifoah.screens.auth.RegisterScreen
import com.project.ifoah.screens.HomeScreen
import com.project.ifoah.screens.SessionStatistics
import com.project.ifoah.screens.SplashScreen
import com.project.ifoah.viewmodels.auth.AuthViewModel
import com.project.ifoah.viewmodels.skidata.SkiDataViewModel


@Composable
fun Navigation(){


    //init nav controller
    val navController = rememberNavController()

    val firebaseAuth = FirebaseAuth.getInstance()
    val firebStore = FirebaseFirestore.getInstance()
    //init view model
    val authViewModel = AuthViewModel(firebaseAuth, firebStore);
    val skiDataViewModel = SkiDataViewModel(firebStore,authViewModel.auth.uid)
    skiDataViewModel.getDataFromDB();
    Log.d("stuff",skiDataViewModel.skiData.toString())

    if(authViewModel.singIn.value){

        val stuff = authViewModel
        Log.d("myuserdata", stuff.userData.toString())
        NavHost(navController = navController, startDestination = "${SCREENS.Splash}"){
            composable("${SCREENS.Login}"){ LoginScreen(navController = navController, authViewModel = authViewModel)}
            composable("${SCREENS.Register}"){RegisterScreen(navController, viewModel = authViewModel)}
            composable("${SCREENS.Home}"){ HomeScreen(navController = navController,authViewModel = authViewModel, skiDataViewModel = skiDataViewModel) }
            composable("${SCREENS.Splash}"){ SplashScreen(navController = navController,authViewModel = authViewModel) }
            composable("${SCREENS.SkiStatistics}"){SessionStatistics(navController = navController, authViewModel = authViewModel, skiDataViewModel = skiDataViewModel)}
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
                HomeScreen(
                    navController = navController,
                    authViewModel = authViewModel,
                    skiDataViewModel = skiDataViewModel
                )
            }

        }
    }

}