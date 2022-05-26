package com.project.ifoah.widgets.statistics

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.project.ifoah.viewmodels.skidata.Turn
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SessionTurns(turns : List<Turn>){
    val avgSpeed = turns.stream().mapToInt{
        it.avgSpeed.toInt()
    }.average().orElse(0.0)

    val avgAngle = turns.map{
       it -> when(it.maxAngle.toInt() <0){
            true -> it.maxAngle.toInt() *-1
            else -> it.maxAngle.toInt()
        }
    }.average()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 250.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Average session speed: ${avgSpeed.toInt()} km/h", style = MaterialTheme.typography.body1)
        Text("Average angle:  ${avgAngle.toInt()}°",  style = MaterialTheme.typography.body1)

        val scrollstate = rememberScrollState()
        Row(
            modifier = Modifier
                .horizontalScroll(scrollstate)
                .fillMaxWidth().padding(10.dp)
        ){
            turns.forEach{
                Column(
                    modifier = Modifier.width(70.dp).padding(10.dp).fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val iconColor :Color
                    val tmpTurn = if(it.maxAngle.toInt() < 0) it.maxAngle.toInt() *-1
                    else it.maxAngle.toInt()

                    if(tmpTurn >= 69) iconColor = Color.Green
                    else if (tmpTurn < 69 && tmpTurn >= 55) iconColor = Color.Yellow
                    else if (tmpTurn < 55) iconColor = Color.Red
                    else iconColor = Color.White

                    if(it.maxAngle.toInt() < 0){
                        Icon(
                            imageVector = Icons.Default.TurnSlightLeft,
                            contentDescription = "account",
                            tint = iconColor,
                            modifier = Modifier.size(45.dp)
                        )
                    }else{
                        Icon(
                            imageVector = Icons.Default.TurnSlightRight,
                            contentDescription = "account",
                            tint = iconColor,
                            modifier = Modifier.size(45.dp)
                        )
                    }
                    Text(text ="$tmpTurn°")
                }

            }
        }

    }


}

