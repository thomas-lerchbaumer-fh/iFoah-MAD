package com.project.ifoah.screens.progress

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.madrapps.plot.line.DataPoint
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot
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
import java.sql.ResultSet
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@Composable
fun ProgressScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    skiDataViewModel: SkiDataViewModel
) {
    DrawerMenu(
        navController = navController,
        authViewModel = authViewModel,
        title = "Your Progress"
    ) {
        LazyColumn() {
            item {
                BarStats(skiDataViewModel = skiDataViewModel)
            }
            item {
                LineStats(skiDataViewModel = skiDataViewModel)
            }
        }
    }
}

@Composable
fun BarStats(skiDataViewModel: SkiDataViewModel) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .heightIn(min = 200.dp, max = 300.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Total Turns per day", modifier = Modifier.padding(bottom = 20.dp))

            BarChartView(skiDataViewModel = skiDataViewModel)
        }
    }
}

@Composable
fun BarChartView(skiDataViewModel: SkiDataViewModel) {
    val barDataSessionTurns = mutableMapOf<String, Int>()

    skiDataViewModel.skiData.forEach { (key, session) ->
        val date = key
        var totalTurns = session.sumOf {
            it.totalTurns.count()
        }
        barDataSessionTurns.put(date, totalTurns)
    }

    Log.d("bardata", barDataSessionTurns.toString())

    val barDataList = mutableListOf<BarChartData.Bar>()

    barDataSessionTurns.forEach { (key, value) ->
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
        yAxisDrawer = SimpleYAxisDrawer(axisLineColor = Color.White, labelTextColor = Color.White,),
        labelDrawer = SimpleLabelDrawer(labelTextColor = Color.White)
    )
}

@Composable
fun LineStats(skiDataViewModel: SkiDataViewModel) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .heightIn(min = 200.dp, max = 300.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Averange angle per day", modifier = Modifier.padding(bottom = 20.dp))
            LineChartView(skiDataViewModel = skiDataViewModel)
        }
    }
}

@Composable
fun LineChartView(skiDataViewModel: SkiDataViewModel) {
    val visibility = remember { mutableStateOf(false) }
    val cardWidth = remember { mutableStateOf(0) }
    val xOffset = remember { mutableStateOf(0f) }
    val points = remember { mutableStateOf(listOf<DataPoint>()) }


    val lineChartList2 = mutableListOf<DataPoint>()
    var cnt = 0;
    skiDataViewModel.skiData.forEach { (key, session) ->
        val avgAnglePerSession = session.map {
            it.totalTurns.map { turn ->
                when (turn.maxAngle.toInt() < 0) {
                    true -> turn.maxAngle.toInt() * -1
                    else -> turn.maxAngle.toInt()
                }
            }.sum()
        }

        var totalTurns = session.sumOf {
            it.totalTurns.count()
        }
        val avgAngleDay = avgAnglePerSession.sum() / totalTurns
        var tmpTime = key.replaceFirst("-", ".");
        tmpTime = tmpTime.replaceAfter("-", "")
        tmpTime = tmpTime.replace("-", "")
        lineChartList2.add(DataPoint(tmpTime.toFloat(), avgAngleDay.toFloat()))
        cnt++
    }

    Box(Modifier.fillMaxHeight()) {
        Column(modifier = Modifier.fillMaxHeight()) {
            LineGraph(
                plot = LinePlot(
                    listOf(
                        LinePlot.Line(
                            lineChartList2,
                            LinePlot.Connection(color = MaterialTheme.colors.primary),
                            LinePlot.Intersection(color = MaterialTheme.colors.primary),
                            LinePlot.Highlight(color = Color.White),
                        )
                    ),
                    grid = LinePlot.Grid(MaterialTheme.colors.background, steps = 4),
                    horizontalExtraSpace = 20.dp,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.White),
                onSelection = { xLine, pnt ->

                    points.value = pnt
                    xOffset.value = xLine
                },
                onSelectionStart = { visibility.value = true },
                onSelectionEnd = { visibility.value = false },
            )
        }
        if (visibility.value) {
            Surface(
                modifier = Modifier
                    .width(200.dp)
                    .align(Alignment.BottomCenter)
                    .onGloballyPositioned {
                        cardWidth.value = it.size.width
                    }
                    .graphicsLayer(translationX = xOffset.value),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colors.primary
            ) {
                Column(
                    Modifier
                        .padding(horizontal = 8.dp)
                ) {
                    val value = points.value
                    if (value.isNotEmpty()) {
                        val x = DecimalFormat("#.#").format(value[0].x)
                        Text(
                            modifier = Modifier.padding(vertical = 8.dp),
                            text = "Average angle on $x: ${value[0].y}",
                            style = MaterialTheme.typography.body2,
                            color = Color.White
                        )
                    }
                }
            }

        }

    }


    /*LineChart(
        lineChartData = LineChartData(
            points = lineChartList
        ),
        // Optional properties.
        modifier = Modifier.fillMaxSize(),
        animation = simpleChartAnimation(),
        pointDrawer = FilledCircularPointDrawer(color = MaterialTheme.colors.primary),
        lineDrawer = SolidLineDrawer(color = Color.White),

    )*/
}
