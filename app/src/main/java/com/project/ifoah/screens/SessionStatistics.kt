package com.project.ifoah.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.project.ifoah.viewmodels.auth.AuthViewModel
import com.project.ifoah.viewmodels.skidata.SkiDataViewModel
import com.project.ifoah.widgets.DeviceStatus
import com.project.ifoah.widgets.home.SkiSessionWidget
import com.project.ifoah.widgets.menu.DrawerMenu

@Composable
fun SessionStatistics(navController: NavController, authViewModel: AuthViewModel, skiDataViewModel : SkiDataViewModel){
    val name = authViewModel.userData.value

    Log.d("stuff",skiDataViewModel.skiData.toString())
    DrawerMenu(navController = navController, authViewModel = authViewModel, title = "Statistics"){
        Column(){

        }

    }


}