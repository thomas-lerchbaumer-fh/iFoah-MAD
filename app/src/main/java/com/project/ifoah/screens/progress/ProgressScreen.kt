package com.project.ifoah.screens.progress

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.ifoah.navigation.SCREENS
import com.project.ifoah.screens.SingleSession
import com.project.ifoah.viewmodels.auth.AuthViewModel
import com.project.ifoah.viewmodels.skidata.SkiDataViewModel
import com.project.ifoah.widgets.menu.DrawerMenu
import me.bytebeats.views.charts.LabelFormatter
import me.bytebeats.views.charts.bar.BarChart
import me.bytebeats.views.charts.bar.BarChartData
import me.bytebeats.views.charts.bar.render.bar.SimpleBarDrawer
import me.bytebeats.views.charts.bar.render.label.SimpleLabelDrawer
import me.bytebeats.views.charts.bar.render.xaxis.IXAxisDrawer
import me.bytebeats.views.charts.bar.render.xaxis.SimpleXAxisDrawer
import me.bytebeats.views.charts.bar.render.yaxis.SimpleYAxisDrawer
import me.bytebeats.views.charts.line.LineChart
import me.bytebeats.views.charts.line.LineChartData
import me.bytebeats.views.charts.line.render.line.SolidLineDrawer
import me.bytebeats.views.charts.line.render.point.FilledCircularPointDrawer
import me.bytebeats.views.charts.simpleChartAnimation


@Composable
fun ProgressScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    skiDataViewModel: SkiDataViewModel
) {
    DrawerMenu(navController = navController, authViewModel = authViewModel, title = "Your Progress") {
        LazyColumn() {
                item {
                    BarStats(skiDataViewModel = skiDataViewModel)
                }
                item{
                    LineStats(skiDataViewModel = skiDataViewModel)
                }
        }
    }
}

@Composable
fun BarStats(skiDataViewModel: SkiDataViewModel){
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .heightIn(min = 200.dp, max = 300.dp)

    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text="Total Turns per day",modifier = Modifier.padding(bottom = 20.dp))

            BarChartView(skiDataViewModel = skiDataViewModel)
        }
    }
}

@Composable
fun BarChartView(skiDataViewModel: SkiDataViewModel) {
    val barDataSessionTurns = mutableMapOf<String,Int>()

    skiDataViewModel.skiData.forEach{ (key,session) ->
        val date = key
        var totalTurns = session.sumOf{
            it.totalTurns.count()
        }
        barDataSessionTurns.put(date,totalTurns)
    }

    Log.d("bardata",barDataSessionTurns.toString())

    val barDataList = mutableListOf<BarChartData.Bar>()

    barDataSessionTurns.forEach{(key,value)->
        val tmpBar = BarChartData.Bar(
            label = key,
            value = value.toFloat(),
            color = MaterialTheme.colors.primary
        )
        barDataList.add(tmpBar)
    }

    BarChart(
        barChartData = BarChartData(
            bars = barDataList
        ),
        // Optional properties.
        modifier = Modifier.fillMaxSize(),
        animation = simpleChartAnimation(),
        barDrawer = SimpleBarDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(axisLineColor = Color.White),
        yAxisDrawer = SimpleYAxisDrawer(axisLineColor = Color.White, labelTextColor = Color.White),
        labelDrawer = SimpleLabelDrawer(labelTextColor = Color.White)
    )
}

@Composable
fun LineStats(skiDataViewModel: SkiDataViewModel){
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .heightIn(min = 200.dp, max = 300.dp)

    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text="Averange angle per day",modifier = Modifier.padding(bottom = 20.dp))
            LineChartView(skiDataViewModel = skiDataViewModel)
        }
    }
}

@Composable
fun LineChartView(skiDataViewModel: SkiDataViewModel) {
    val barDataSessionTurns = mutableMapOf<String,Int>()
    val lineChartList = mutableListOf<LineChartData.Point>()
    skiDataViewModel.skiData.forEach{ (key,session) ->
        val avgAnglePerSession = session.map{
            it.totalTurns.map { turn->
                when(turn.maxAngle.toInt() <0){
                    true -> turn.maxAngle.toInt() *-1
                    else -> turn.maxAngle.toInt()
                }
            }.sum()
        }


        var totalTurns = session.sumOf{
            it.totalTurns.count()
        }
        val avgAngleDay = avgAnglePerSession.sum() / totalTurns
        Log.d("avgAngleDay", avgAnglePerSession.sum().toString());
        Log.d("avgAngleDay", totalTurns.toString());
        lineChartList.add(LineChartData.Point(avgAngleDay.toFloat(),key))
    }

    Log.d("bardata",barDataSessionTurns.toString())




    LineChart(
        lineChartData = LineChartData(
            points = lineChartList
        ),
        // Optional properties.
        modifier = Modifier.fillMaxSize(),
        animation = simpleChartAnimation(),
        pointDrawer = FilledCircularPointDrawer(color = MaterialTheme.colors.primary),
        lineDrawer = SolidLineDrawer(color = Color.White),

    )
}
