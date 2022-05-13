package com.project.ifoah.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun TopBar(navController: NavController, title : String, content: @Composable () -> Unit = {} ){

    Scaffold(
        topBar = {
            TopAppBar(
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                    Text(text = title, style = MaterialTheme.typography.h6)
                }
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
            ){
                content()
            }
        }

        )

}