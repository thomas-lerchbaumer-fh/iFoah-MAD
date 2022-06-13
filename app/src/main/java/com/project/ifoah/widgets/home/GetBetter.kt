package com.project.ifoah.widgets.home


import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.Insets.add
import androidx.navigation.NavController
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.size.OriginalSize
import com.project.ifoah.R
import com.project.ifoah.navigation.SCREENS
import com.project.ifoah.viewmodels.auth.AuthViewModel
import com.project.ifoah.viewmodels.skidata.SkiDataViewModel



@Composable
fun GetBetterWidget(navController: NavController, authViewModel: AuthViewModel){

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
                    navController.navigate(route = "${SCREENS.GetBetterScreen}")
                },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.background),

                ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Ski like Hansi", style = MaterialTheme.typography.h6)
                    }
                    Spacer(Modifier.height(50.0.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,

                        ) {
                        Icon(
                            imageVector = Icons.Default.FitnessCenter,
                            contentDescription = "account",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.size(40.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.YoutubeSearchedFor,
                            contentDescription = "account",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.size(40.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Videocam,
                            contentDescription = "account",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.size(40.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.SportsBar,
                            contentDescription = "account",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
                Image(
                    painter = painterResource(R.drawable.hansi_3),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    alpha = 0.2f
                )


                //AsyncImage(model = "https://c.tenor.com/WIwRwFVe5J0AAAAd/hansi-himterseer-ski.gif", contentDescription = "skifoan", imageLoader = imageLoader)
            }
        }
    }


}