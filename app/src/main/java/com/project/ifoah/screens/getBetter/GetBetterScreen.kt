package com.project.ifoah.screens.getBetter



import android.net.Uri
import android.telecom.Call
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.project.ifoah.viewmodels.auth.AuthViewModel
import com.project.ifoah.widgets.menu.DrawerMenu
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.project.ifoah.data.GetBetter
import com.project.ifoah.data.getGetBetter
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.project.ifoah.navigation.SCREENS

@Composable
fun GetBetterScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
) {
    DrawerMenu(
        navController = navController,
        authViewModel = authViewModel,
        title = "Ski like Hansi"
    ) {
        LazyColumn() {
            items(getGetBetter()) { item ->
                Categories(item, showDetails = false,){
                    navController.navigate(route = "${SCREENS.VideoplayerScreen}/${item.id}")
                }
            }
        }
    }
}

@Composable
fun Categories(videoItem: GetBetter, showDetails: Boolean, onItemClick: (String) -> Unit = {}) {
    var showDetails by remember{
        mutableStateOf(showDetails)
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { onItemClick(videoItem.id) },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .size(120.dp)
                    .padding(12.dp),
                elevation = 6.dp,
                shape = RoundedCornerShape(corner = CornerSize(60.dp)),
            ){
                Image(
                    painter = rememberImagePainter( // Coil Part to load images
                        data = videoItem.imgSource,
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = "Movie poster",
                    modifier = Modifier.size(10.dp)
                )
            }
            Column(
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            ) {
                Text(text = videoItem.title, style= MaterialTheme.typography.h6)
                AnimatedVisibility(visible = showDetails,
                    enter = fadeIn(),
                    exit = slideOutHorizontally() + shrinkVertically() + fadeOut()
                ) {
                    VideoDetails(videoItem)
                }
                Icons.Default.FavoriteBorder
                when(showDetails){
                    true -> Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "arrow down", modifier = Modifier.clickable { showDetails =! showDetails  })
                    false-> Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "arrow up", modifier = Modifier.clickable { showDetails =! showDetails  })
                }
            }
        }
    }
}

@Composable
fun VideoDetails(videoItem: GetBetter){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)) {
        Column() {
            Text(text ="Description: ${videoItem.description}", style= MaterialTheme.typography.body2)
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(top =2.dp, bottom = 2.dp))
            Text(text ="Source: ${videoItem.source}", style= MaterialTheme.typography.body2)
        }
    }
}



