package com.project.ifoah.screens.videoplayer

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.project.ifoah.data.getGetBetter
import com.project.ifoah.navigation.SCREENS
import com.project.ifoah.screens.getBetter.Categories
import com.project.ifoah.viewmodels.auth.AuthViewModel
import com.project.ifoah.widgets.menu.DrawerMenu

@Composable
fun VideoplayerScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    videoId : String?
) {
    DrawerMenu(
        navController = navController,
        authViewModel = authViewModel,
        title = "Ski like Hansi"
    ) {
        VideoPlayer(videoId = videoId)
    }
}


@Composable
fun VideoPlayer(videoId : String?){

    Card (
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {  },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
        ) {

        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            val mContext = LocalContext.current
            var myVideoUrl = ""

            if (videoId == getGetBetter()[0].id) {
                myVideoUrl =
                    "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4"
            } else {
                myVideoUrl =
                    "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4"
            }

            val mExoPlayer = remember(mContext) {
                ExoPlayer.Builder(mContext).build().apply {
                    val dataSourceFactory = DefaultDataSourceFactory(
                        mContext,
                        Util.getUserAgent(mContext, mContext.packageName)
                    )
                    val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(Uri.parse(myVideoUrl))
                    prepare(source)
                }
            }

            AndroidView(factory = { context ->
                PlayerView(context).apply {
                    player = mExoPlayer
                }
            })
        }
    }
}