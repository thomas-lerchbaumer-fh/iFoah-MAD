package com.project.ifoah.screens

import android.util.Log
import android.util.MutableBoolean
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.DownhillSkiing
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.ifoah.navigation.SCREENS
import com.project.ifoah.viewmodels.auth.AuthViewModel
import com.project.ifoah.viewmodels.skidata.SkiDataViewModel
import com.project.ifoah.viewmodels.skidata.SkiSession
import com.project.ifoah.viewmodels.skidata.Turn
import com.project.ifoah.widgets.DeviceStatus
import com.project.ifoah.widgets.home.SkiSessionWidget
import com.project.ifoah.widgets.menu.DrawerMenu
import com.project.ifoah.widgets.statistics.SessionTurns
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat


var sheetTurns = mutableListOf<Turn>();

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SessionStatistics(
    navController: NavController,
    authViewModel: AuthViewModel,
    skiDataViewModel: SkiDataViewModel
) {


    val name = authViewModel.userData.value
    val sessions = skiDataViewModel.skiData
    Log.d("sessions", sessions.toString())
    DrawerMenu(navController = navController, authViewModel = authViewModel, title = "Statistics") {


        LazyColumn() {
            sessions.forEach { (key, session) ->

                item {
                    SingleSession(date = key, session = session, navController = navController)
                }
            }
        }
    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SingleSession(date: String, session: List<SkiSession>, navController: NavController) {

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .heightIn(min = 200.dp, max = 400.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Sessions recorded on $date", style = MaterialTheme.typography.h6, modifier = Modifier.padding(top=10.dp))
            var showDetails by remember {
                mutableStateOf(false)
            }
            var turns by rememberSaveable(Unit) {
                mutableStateOf(listOf<Turn>())
            }
            val scrollstate = rememberScrollState()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(scrollstate)
                    .padding(top = 30.dp)
            ) {
                session.forEach { item ->
                    val dateFormat = SimpleDateFormat("HH:mm")
                    val date = dateFormat.format(item.date.time)
                    Card(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .padding(10.dp)
                            .align(alignment = Alignment.CenterVertically)
                            .clickable {
                                turns = item.totalTurns

                                if(showDetails == false){
                                    showDetails = !showDetails
                                }
                            },
                        shape = RoundedCornerShape(10.dp),
                        backgroundColor = MaterialTheme.colors.primary,

                        ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccessTime,
                                contentDescription = "account",
                                tint = Color.White,
                                modifier = Modifier.size(25.dp)
                            )
                            Text(
                                text = date.toString(),
                                style = MaterialTheme.typography.body1,
                                color = Color.White
                            )
                        }

                    }


                }
            }
            if(showDetails) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "account",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(45.dp).clickable {
                        showDetails = false
                    }
                )
            }
            AnimatedVisibility(
                visible = showDetails,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                SessionTurns(turns = turns)
            }
        }
    }
}

