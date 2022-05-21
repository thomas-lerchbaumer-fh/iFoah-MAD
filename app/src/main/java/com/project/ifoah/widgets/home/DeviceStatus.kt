package com.project.ifoah.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Battery6Bar
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.SystemSecurityUpdateGood
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DeviceStatus() {
    var deviceOnline by remember {
        mutableStateOf(false)
    }



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        DeviceHeader()
        DeviceOnline()
    }

}

@Preview
@Composable
fun DeviceOnline() {

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(130.dp),

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.SystemSecurityUpdateGood,
                    contentDescription = "account",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(35.dp)
                )
                Text(text = "Device", style = MaterialTheme.typography.body2)
                Text(text = "connected", style = MaterialTheme.typography.body2)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Sensors, contentDescription = "account",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(35.dp)
                )
                Text(text = "Sensors", style = MaterialTheme.typography.body2)
                Text(text = "connected", style = MaterialTheme.typography.body2)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Battery6Bar, contentDescription = "account",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(35.dp)
                )
                Text(text = "Battery", style = MaterialTheme.typography.body2)
                Text(text = "80%", style = MaterialTheme.typography.body2)
            }
        }
    }


}

@Composable
fun DeviceOffline() {
}

@Preview
@Composable
fun DeviceHeader() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .height(50.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hardware Status", style = MaterialTheme.typography.body2)
        }


    }

}