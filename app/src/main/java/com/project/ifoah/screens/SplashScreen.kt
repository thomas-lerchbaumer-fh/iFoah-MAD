package com.project.ifoah.screens


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DownhillSkiing
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.ifoah.navigation.SCREENS
import com.project.ifoah.viewmodels.auth.AuthViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController, authViewModel: AuthViewModel){
    var start by remember {
        mutableStateOf(false)
    }
    val alpha = animateFloatAsState(targetValue = if(start) 1f else 0f,
        animationSpec =  tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true ){
        start = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(route = "${SCREENS.Home}")


    }

    Splash(alpha.value)
}

@Composable
fun Splash(alpha: Float){
    Box(modifier = Modifier
        .background( MaterialTheme.colors.background)
        .fillMaxSize(),
        contentAlignment = Alignment.Center){
        Icon(
            modifier = Modifier.size(120.dp).alpha(alpha = alpha),
            imageVector = Icons.Default.DownhillSkiing,
            contentDescription = "account",
            tint = MaterialTheme.colors.primary
        )
    }
}