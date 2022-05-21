package com.project.ifoah.widgets.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.ifoah.viewmodels.auth.AuthViewModel
import com.project.ifoah.R
import com.project.ifoah.navigation.SCREENS

@Composable
fun SkiSessionWidget(navController: NavController, authViewModel: AuthViewModel) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
          ,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(200.dp)
                .clickable {
                    navController.navigate(route = "${SCREENS.SkiStatistics}")
                },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.background),

                ) {
                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text("Check out your session statistics", style=MaterialTheme.typography.h6)
                    }
                    Spacer(Modifier.height(50.0.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,

                    ){
                        Icon(
                            imageVector = Icons.Default.DownhillSkiing,
                            contentDescription = "account",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.size(40.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.StackedLineChart,
                            contentDescription = "account",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.size(40.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.PieChart,
                            contentDescription = "account",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.size(40.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Analytics,
                            contentDescription = "account",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
                Image(
                    painter = painterResource(R.drawable.carving_img_session),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    alpha = 0.2f
                )
            }
        }

    }

    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier.horizontalScroll(scrollState)
    ) {
        singleSession()
        singleSession()
        singleSession()
        singleSession()
        singleSession()
    }
}


@Composable
fun singleSession() {

    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Card(
            modifier = Modifier
                .height(120.dp)
                .width(120.dp)
                .background(color = MaterialTheme.colors.primary)
                .padding(20.dp)
        ) {

        }

    }
}