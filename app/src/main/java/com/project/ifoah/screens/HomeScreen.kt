package com.project.ifoah.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import com.project.ifoah.viewmodels.auth.AuthViewModel
import com.project.ifoah.viewmodels.skidata.SkiDataViewModel
import com.project.ifoah.widgets.DeviceStatus
import com.project.ifoah.widgets.home.GetBetterWidget
import com.project.ifoah.widgets.home.ProgressWidget
import com.project.ifoah.widgets.home.SkiSessionWidget
import com.project.ifoah.widgets.menu.DrawerMenu

@Composable
fun HomeScreen(navController: NavController,authViewModel: AuthViewModel, skiDataViewModel : SkiDataViewModel){
    val name = authViewModel.userData.value


   DrawerMenu(navController = navController, authViewModel = authViewModel, title = "Home"){
       Column {
           DeviceStatus()

           val scrollState = rememberScrollState()
           Column(modifier = Modifier.verticalScroll(scrollState)) {
               SkiSessionWidget(navController = navController, authViewModel = authViewModel)
               ProgressWidget(navController = navController, authViewModel = authViewModel)
               GetBetterWidget(navController = navController, authViewModel = authViewModel)
           }
       }
   }
}
